package uno.d1s.yaspeller

import uno.d1s.yaspeller.domain.api.Language
import uno.d1s.yaspeller.domain.api.RequestConfiguration
import uno.d1s.yaspeller.factory.yandexSpeller

internal val yaSpeller = yandexSpeller()

val russian = Language.RUSSIAN
val ukrainian = Language.UKRAINIAN
val english = Language.ENGLISH

suspend fun check(text: String, config: RequestConfiguration.() -> Unit = {}) =
    yaSpeller.checkText(text, RequestConfiguration().apply(config))

suspend fun checks(texts: List<String>, config: RequestConfiguration.() -> Unit = {}) =
    yaSpeller.checkTexts(texts, RequestConfiguration().apply(config))

suspend fun String.checkSpelling(config: RequestConfiguration.() -> Unit = {}) =
    yaSpeller.checkText(this, RequestConfiguration().apply(config))

suspend fun List<String>.checkSpelling(config: RequestConfiguration.() -> Unit = {}) =
    yaSpeller.checkTexts(this, RequestConfiguration().apply(config))

suspend fun suggestSpelling(text: String, config: RequestConfiguration.() -> Unit = {}) =
    text.checkSpelling(config)[0].suggestions

suspend fun String.suggestSpellings(config: RequestConfiguration.() -> Unit = {}) =
    this.checkSpelling(config)[0].suggestions