package dev.d1s.yaspeller.dsl

import dev.d1s.yaspeller.domain.Option
import dev.d1s.yaspeller.dsl.marker.YaSpellerDslMarker

@YaSpellerDslMarker
public class OptionsConfigurationDsl {

    public val optionsSet: MutableSet<Option> = mutableSetOf()

    public var ignoreDigits: Boolean = false
        set(value) {
            this.add(Option.IGNORE_DIGITS, value)
            field = value
        }

    public var ignoreUrls: Boolean = false
        set(value) {
            this.add(Option.IGNORE_URLS, value)
            field = value
        }

    public var ignoreCapitalization: Boolean = false
        set(value) {
            this.add(Option.IGNORE_CAPITALIZATION, value)
            field = value
        }

    private fun add(option: Option, value: Boolean) {
        if (value) {
            optionsSet.add(option)
        }
    }
}