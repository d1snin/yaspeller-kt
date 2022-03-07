package dev.d1s.yaspeller

import dev.d1s.yaspeller.dsl.RequestConfigurationDsl
import dev.d1s.yaspeller.factory.yandexSpeller

internal val yaSpeller = yandexSpeller()

fun configureSpeller(config: RequestConfigurationDsl.() -> Unit = yaSpeller.getDefaultConfiguration()) {
    yaSpeller.setDefaultConfiguration(config)
}

suspend fun String.checkSpelling(config: RequestConfigurationDsl.() -> Unit = yaSpeller.getDefaultConfiguration()) =
    yaSpeller.checkText(this, config)