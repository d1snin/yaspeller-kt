package dev.d1s.yaspeller.dsl

import dev.d1s.yaspeller.domain.Option
import dev.d1s.yaspeller.dsl.marker.YaSpellerDslMarker

@YaSpellerDslMarker
public class OptionsConfigurationDsl {

    public val optionsSet: MutableSet<Option> = mutableSetOf<Option>()

    public var ignoreDigits: Boolean = false
        set(value) {
            value.add(Option.IGNORE_DIGITS)
            field = value
        }

    public var ignoreUrls: Boolean = false
        set(value) {
            value.add(Option.IGNORE_URLS)
            field = value
        }

    public var ignoreCapitalization: Boolean = false
        set(value) {
            value.add(Option.IGNORE_CAPITALIZATION)
            field = value
        }

    private fun Boolean.add(option: Option) {
        if (this) {
            optionsSet.add(option)
        }
    }
}