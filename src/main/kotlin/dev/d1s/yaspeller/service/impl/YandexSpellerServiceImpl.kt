package dev.d1s.yaspeller.service.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import dev.d1s.yaspeller.constant.*
import dev.d1s.yaspeller.domain.api.TextSpellCheckResult
import dev.d1s.yaspeller.domain.api.WordSpellCheckResult
import dev.d1s.yaspeller.domain.internal.InternalSpellCheckResult
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl
import dev.d1s.yaspeller.exception.SpellCheckFailedException
import dev.d1s.yaspeller.exception.TooLongTextException
import dev.d1s.yaspeller.service.YandexSpellerService
import dev.d1s.yaspeller.util.setParameter


internal object YandexSpellerServiceImpl : YandexSpellerService {

    private val gson = Gson()

    private val httpClient = OkHttpClient()

    private var defaultConfig: RequestConfigurationDsl.() -> Unit = {}

    override suspend fun checkText(
        text: String,
        configuration: RequestConfigurationDsl.() -> Unit
    ): TextSpellCheckResult = withContext(Dispatchers.IO) {
        if (text.length > MAX_TEXT_LENGTH) {
            throw TooLongTextException
        }

        try {
            val results = this@YandexSpellerServiceImpl.getSpellCheckResults(configuration, text)

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
                this@YandexSpellerServiceImpl.getImprovedText(results, text)
            )
        } catch (ex: Exception) {
            throw SpellCheckFailedException("Spell check failed: ${ex.message ?: "no message provided."}")
        }
    }

    override fun setDefaultConfiguration(configuration: RequestConfigurationDsl.() -> Unit) {
        defaultConfig = configuration
    }

    override fun getDefaultConfiguration(): RequestConfigurationDsl.() -> Unit = defaultConfig

    private fun getSpellCheckResults(
        configuration: RequestConfigurationDsl.() -> Unit,
        text: String
    ): List<WordSpellCheckResult> {
        val config = RequestConfigurationDsl().apply(defaultConfig).apply(configuration).toInternal()

        return gson.fromJson<ArrayList<InternalSpellCheckResult>>(
            httpClient.newCall(
                Request.Builder()
                    .url(
                        BASE_URL
                            .setParameter(PARAMETER_LANGUAGES, config.languages)
                            .setParameter(PARAMETER_TEXT, text)
                            .setParameter(PARAMETER_OPTIONS, config.options.toString())
                            .setParameter(PARAMETER_FORMAT, config.format)
                    ).get().build()
            ).execute().use {
                it.body!!.string()
            },
            object : TypeToken<ArrayList<InternalSpellCheckResult>>() {}.type
        ).map {
            it.toApi()
        }
    }

    private fun getImprovedText(results: List<WordSpellCheckResult>, text: String): String {
        var improvedText = text

        results.forEach {
            improvedText = improvedText.replace(it.word, it.firstSuggestion)
        }

        return improvedText
    }
}