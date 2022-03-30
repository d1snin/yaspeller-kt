package dev.d1s.yaspeller.domain

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class OptionTest {

    @Test
    fun `should return valid option values`() {
        expectThat(Option.IGNORE_DIGITS.optionValue) isEqualTo 2
        expectThat(Option.IGNORE_URLS.optionValue) isEqualTo 4
        expectThat(Option.IGNORE_CAPITALIZATION.optionValue) isEqualTo 512
    }
}