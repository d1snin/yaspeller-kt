package dev.d1s.yaspeller.api

import dev.d1s.yaspeller.domain.result.TextSpellCheckResult
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl

public interface YandexSpeller {

    public suspend fun checkSpelling(text: String): TextSpellCheckResult

    public fun updateConfiguration(configuration: RequestConfigurationDsl.() -> Unit)
}