package uno.d1s.yaspeller.service.impl

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import uno.d1s.yaspeller.constant.*
import uno.d1s.yaspeller.domain.api.RequestConfiguration
import uno.d1s.yaspeller.domain.api.SpellCheckResult
import uno.d1s.yaspeller.domain.internal.InternalSpellCheckResult
import uno.d1s.yaspeller.exception.SpellCheckFailedException
import uno.d1s.yaspeller.exception.TooLongTextException
import uno.d1s.yaspeller.service.YandexSpellerService
import uno.d1s.yaspeller.util.toCommaSeparatedString

internal object YandexSpellerServiceImpl : YandexSpellerService {

    private val httpClient: HttpClient = HttpClient(OkHttp) {
        install(JsonFeature)

        // all of this just because of "Header(s) [Content-Type] are controlled by the engine and cannot be set explicitly"
        engine {
            addNetworkInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json").build()
                )
            }
        }
    }

    override suspend fun checkText(text: String, configuration: RequestConfiguration): List<SpellCheckResult> {
        if (text.length > MAX_TEXT_LENGTH) {
            throw TooLongTextException
        }

        return try {
            httpClient.post<List<InternalSpellCheckResult>>(BASE_URL) {
                parameter(PARAMETER_LANGUAGE, configuration.languages.map {
                    it.parameter
                }.toCommaSeparatedString())
                parameter(PARAMETER_TEXT, text)
                parameter(PARAMETER_OPTIONS,
                    configuration.options.sumOf {
                        it.optionValue
                    }
                )
                parameter(PARAMETER_FORMAT, configuration.format.parameter)
            }.map {
                it.toApi()
            }
        } catch (ex: Exception) {
            throw SpellCheckFailedException("Spell check failed: ${ex.message ?: "no message provided."}")
        }
    }

    override suspend fun checkTexts(
        texts: List<String>,
        configuration: RequestConfiguration
    ): List<List<SpellCheckResult>> =
        texts.map {
            this.checkText(it, configuration)
        }
}