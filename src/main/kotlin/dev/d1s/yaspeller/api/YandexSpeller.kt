package dev.d1s.yaspeller.api

import dev.d1s.yaspeller.domain.result.TextSpellCheckResult
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl
import dev.d1s.yaspeller.dsl.marker.YaSpellerDslMarker

public interface YandexSpeller {

    public suspend fun checkSpelling(text: String): TextSpellCheckResult

    @YaSpellerDslMarker
    public fun updateConfiguration(configuration: RequestConfigurationDsl.() -> Unit)
}