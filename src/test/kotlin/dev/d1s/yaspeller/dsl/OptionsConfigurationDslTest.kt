package dev.d1s.yaspeller.dsl

import dev.d1s.yaspeller.domain.Option
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isEmpty
import strikt.assertions.isFalse
import kotlin.test.Test

internal class OptionsConfigurationDslTest {

    @Test
    fun `should add options`() {
        val dsl = OptionsConfigurationDsl()

        expectThat(dsl.optionsSet).isEmpty()

        expectThat(dsl.ignoreDigits).isFalse()

        dsl.ignoreDigits = true

        expectThat(dsl.optionsSet)
            .containsExactly(Option.IGNORE_DIGITS)

        expectThat(dsl.ignoreUrls).isFalse()

        dsl.ignoreUrls = true

        expectThat(dsl.optionsSet)
            .containsExactly(Option.IGNORE_DIGITS, Option.IGNORE_URLS)

        expectThat(dsl.ignoreCapitalization).isFalse()

        dsl.ignoreCapitalization = true

        expectThat(dsl.optionsSet)
            .containsExactly(
                Option.IGNORE_DIGITS,
                Option.IGNORE_URLS,
                Option.IGNORE_CAPITALIZATION
            )
    }
}