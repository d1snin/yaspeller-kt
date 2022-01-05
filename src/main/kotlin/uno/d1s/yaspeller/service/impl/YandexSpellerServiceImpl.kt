package uno.d1s.yaspeller.service.impl

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import uno.d1s.yaspeller.constant.*
import uno.d1s.yaspeller.domain.api.TextSpellCheckResult
import uno.d1s.yaspeller.domain.internal.InternalSpellCheckResult
import uno.d1s.yaspeller.dsl.RequestConfigurationDsl
import uno.d1s.yaspeller.exception.SpellCheckFailedException
import uno.d1s.yaspeller.exception.TooLongTextException
import uno.d1s.yaspeller.service.YandexSpellerService

internal object YandexSpellerServiceImpl : YandexSpellerService {

    private var defaultConfig: RequestConfigurationDsl.() -> Unit = {}

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

    override suspend fun checkText(
        text: String,
        configuration: RequestConfigurationDsl.() -> Unit
    ): TextSpellCheckResult {
        if (text.length > MAX_TEXT_LENGTH) {
            throw TooLongTextException
        }

        val config = RequestConfigurationDsl().apply(defaultConfig).apply(configuration).toInternal()

        try {
            val results = httpClient.post<List<InternalSpellCheckResult>>(BASE_URL) {
                parameter(PARAMETER_LANGUAGE, config.languages)
                parameter(PARAMETER_TEXT, text)
                parameter(PARAMETER_OPTIONS, config.options)
                parameter(PARAMETER_FORMAT, config.format)
            }.map {
                it.toApi()
            }

            var improvedText = text

            results.forEach {
                improvedText = improvedText.replace(it.word, it.firstSuggestion)
            }

            return TextSpellCheckResult(
                results.isEmpty(),
                results.size,
                results.map {
                    it.word
                },
                results.map {
                    it.firstSuggestion
                },
                results,
                improvedText
            )
        } catch (ex: Exception) {
            throw SpellCheckFailedException("Spell check failed: ${ex.message ?: "no message provided."}")
        }
    }

    override fun setDefaultConfiguration(configuration: RequestConfigurationDsl.() -> Unit) {
        defaultConfig = configuration
    }

    override fun getDefaultConfiguration(): RequestConfigurationDsl.() -> Unit = defaultConfig
}