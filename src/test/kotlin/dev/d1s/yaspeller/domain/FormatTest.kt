package dev.d1s.yaspeller.domain

import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class FormatTest {

    @Test
    fun `should return valid parameter values`() {
        expectThat(Format.PLAIN_TEXT.parameter) isEqualTo "plain"
        expectThat(Format.HTML.parameter) isEqualTo "html"
    }
}