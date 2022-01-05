package uno.d1s.yaspeller.service

import uno.d1s.yaspeller.domain.api.TextSpellCheckResult
import uno.d1s.yaspeller.dsl.RequestConfigurationDsl

internal interface YandexSpellerService {

    suspend fun checkText(
        text: String,
        configuration: RequestConfigurationDsl.() -> Unit
    ): TextSpellCheckResult

    fun setDefaultConfiguration(configuration: RequestConfigurationDsl.() -> Unit)

    fun getDefaultConfiguration(): RequestConfigurationDsl.() -> Unit
}