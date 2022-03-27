package dev.d1s.yaspeller.service

import dev.d1s.yaspeller.domain.result.TextSpellCheckResult
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl

internal interface YandexSpellerService {

    suspend fun checkText(text: String): TextSpellCheckResult

    fun updateConfiguration(configuration: RequestConfigurationDsl.() -> Unit)
}