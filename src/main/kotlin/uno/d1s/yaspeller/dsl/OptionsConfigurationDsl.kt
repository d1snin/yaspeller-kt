package dev.d1s.yaspeller.dsl

import dev.d1s.yaspeller.domain.api.Option
import dev.d1s.yaspeller.dsl.marker.YaSpellerDslMarker

@YaSpellerDslMarker
class OptionsConfigurationDsl {

    internal val optionsSet = mutableSetOf<Option>()

    var ignoreDigits: Boolean = false
        set(value) {
            value.add(Option.IGNORE_DIGITS)
            field = value
        }

    var ignoreUrls: Boolean = false
        set(value) {
            value.add(Option.IGNORE_URLS)
            field = value
        }

    var ignoreCapitalization: Boolean = false
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