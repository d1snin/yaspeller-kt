package dev.d1s.yaspeller.dsl

import dev.d1s.yaspeller.configuration.InternalRequestConfiguration
import dev.d1s.yaspeller.domain.Format
import dev.d1s.yaspeller.domain.Language
import dev.d1s.yaspeller.domain.Option
import dev.d1s.yaspeller.dsl.marker.YaSpellerDslMarker
import dev.d1s.yaspeller.util.toCommaSeparatedString

@YaSpellerDslMarker
public class RequestConfigurationDsl {

    private var languages: Set<Language> = setOf(Language.RUSSIAN, Language.ENGLISH)
    private var options: Set<Option> = setOf()
    private var format: Format = Format.PLAIN_TEXT

    public val plain: Format = Format.PLAIN_TEXT
    public val html: Format = Format.HTML

    public fun languages(languagesConfig: LanguagesConfigurationDsl.() -> Unit) {
        languages = LanguagesConfigurationDsl().apply(languagesConfig).languageSet
    }

    public fun options(optionsConfig: OptionsConfigurationDsl.() -> Unit) {
        options = OptionsConfigurationDsl().apply(optionsConfig).optionsSet
    }

    public fun format(formatConfig: () -> Format) {
        format = formatConfig()
    }

    internal fun toInternalConfiguration() = InternalRequestConfiguration(
        languages.map {
            it.parameter
        }.toCommaSeparatedString(),

        options.sumOf {
            it.optionValue
        },

        format.parameter
    )
}