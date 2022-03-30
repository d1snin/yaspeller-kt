package dev.d1s.yaspeller.domain

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class LanguageTest {

    @Test
    fun `should return valid parameter values`() {
        expectThat(Language.RUSSIAN.parameter) isEqualTo "ru"
        expectThat(Language.UKRAINIAN.parameter) isEqualTo "uk"
        expectThat(Language.ENGLISH.parameter) isEqualTo "en"
    }
}