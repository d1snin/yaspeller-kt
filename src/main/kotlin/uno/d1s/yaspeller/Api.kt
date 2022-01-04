package uno.d1s.yaspeller

import uno.d1s.yaspeller.dsl.RequestConfigurationDsl
import uno.d1s.yaspeller.factory.yandexSpeller

internal val yaSpeller = yandexSpeller()
internal var defaultConfig: RequestConfigurationDsl.() -> Unit = {}

fun configureSpeller(config: RequestConfigurationDsl.() -> Unit) {
    defaultConfig = config
}

suspend fun String.checkSpelling(config: RequestConfigurationDsl.() -> Unit = defaultConfig) =
    yaSpeller.checkText(this, config)