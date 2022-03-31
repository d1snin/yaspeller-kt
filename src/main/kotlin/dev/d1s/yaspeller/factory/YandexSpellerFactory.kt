package dev.d1s.yaspeller.factory

import dev.d1s.yaspeller.api.YandexSpeller
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl
import dev.d1s.yaspeller.dsl.marker.YaSpellerDslMarker
import dev.d1s.yaspeller.impl.YandexSpellerImpl

public fun yandexSpeller(): YandexSpeller = YandexSpellerImpl()

@YaSpellerDslMarker
public fun yandexSpeller(configuration: RequestConfigurationDsl.() -> Unit): YandexSpeller =
    YandexSpellerImpl().apply {
        updateConfiguration(configuration)
    }