package dev.d1s.yaspeller.service.impl

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.google.gson.reflect.TypeToken
import dev.d1s.yaspeller.constant.*
import dev.d1s.yaspeller.domain.result.TextSpellCheckResult
import dev.d1s.yaspeller.domain.result.WordSpellCheckResult
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl
import dev.d1s.yaspeller.exception.SpellCheckFailedException
import dev.d1s.yaspeller.factory.defaultRequestConfiguration
import dev.d1s.yaspeller.factory.gson
import dev.d1s.yaspeller.service.YandexSpellerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicReference

internal class YandexSpellerServiceImpl : YandexSpellerService {

    private val requestConfiguration: AtomicReference<RequestConfigurationDsl> = AtomicReference(
        defaultRequestConfiguration()
    )

    private val gson = gson()

    override suspend fun checkText(text: String): TextSpellCheckResult = withContext(Dispatchers.IO) {
        if (text.length > MAX_TEXT_LENGTH) {
            throw SpellCheckFailedException("Provided text is too long. Should be less than $MAX_TEXT_LENGTH")
        }

        val results = this@YandexSpellerServiceImpl.getSpellCheckResults(text)

        return@withContext TextSpellCheckResult(
            results.isEmpty(),
            results.size,
            results.map {
                it.word
            },
            results.map {
                it.firstSuggestion
            },
            results,
            text.improved(results)
        )
    }

    override fun updateConfiguration(configuration: RequestConfigurationDsl.() -> Unit) {
        requestConfiguration.set(requestConfiguration.get().apply(configuration))
    }

    private fun getSpellCheckResults(text: String): List<WordSpellCheckResult> {
        val config = requestConfiguration.get().toInternalConfiguration()

        val (_, _, result) = Fuel.get(
            BASE_URL,
            listOf(
                PARAMETER_LANGUAGES to config.languages,
                PARAMETER_TEXT to text,
                PARAMETER_OPTIONS to config.options.toString(),
                PARAMETER_FORMAT to config.format
            )
        ).header(Headers.ACCEPT to "application/json")
            .response()

        val (bytes, error) = result

        error?.let {
            throw SpellCheckFailedException(it.response.toString())
        }

        return gson.fromJson(
            String(bytes!!),
            object : TypeToken<List<WordSpellCheckResult>>() {}.type
        )
    }

    private fun String.improved(results: List<WordSpellCheckResult>): String {
        var improvedText = this

        results.forEach {
            improvedText = improvedText.replace(it.word, it.firstSuggestion)
        }

        return improvedText
    }
}