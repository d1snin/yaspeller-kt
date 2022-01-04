package uno.d1s.yaspeller.dsl

import uno.d1s.yaspeller.domain.api.Format
import uno.d1s.yaspeller.domain.api.Language
import uno.d1s.yaspeller.domain.api.Option
import uno.d1s.yaspeller.domain.internal.InternalRequestConfiguration
import uno.d1s.yaspeller.dsl.marker.YaSpellerDslMarker
import uno.d1s.yaspeller.util.toCommaSeparatedString

@YaSpellerDslMarker
class RequestConfigurationDsl {
    private var languages: Set<Language> = setOf(Language.RUSSIAN, Language.ENGLISH)
    private var options: Set<Option> = setOf()
    private var format: Format = Format.PLAIN_TEXT

    val plain = Format.PLAIN_TEXT
    val html = Format.HTML

    fun languages(languagesConfig: LanguagesConfigurationDsl.() -> Unit) {
        languages = LanguagesConfigurationDsl().apply(languagesConfig).languageSet
    }

    fun options(optionsConfig: OptionsConfigurationDsl.() -> Unit) {
        options = OptionsConfigurationDsl().apply(optionsConfig).optionsSet
    }

    fun format(formatConfig: () -> Format) {
        format = formatConfig()
    }

    internal fun toInternal() = InternalRequestConfiguration(
        languages.map {
            it.parameter
        }.toCommaSeparatedString(),
        options.sumOf {
            it.optionValue
        },
        format.parameter
    )
}