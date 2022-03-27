package dev.d1s.yaspeller.impl

import dev.d1s.yaspeller.api.YandexSpeller
import dev.d1s.yaspeller.domain.result.TextSpellCheckResult
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl
import dev.d1s.yaspeller.factory.yandexSpellerService

internal class YandexSpellerImpl : YandexSpeller {

    private val yandexSpellerService = yandexSpellerService()

    override suspend fun checkSpelling(text: String): TextSpellCheckResult =
        yandexSpellerService.checkText(text)

    override fun updateConfiguration(configuration: RequestConfigurationDsl.() -> Unit) =
        yandexSpellerService.updateConfiguration(configuration)
}