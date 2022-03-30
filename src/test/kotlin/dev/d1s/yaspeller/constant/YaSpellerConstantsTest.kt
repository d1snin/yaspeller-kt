package dev.d1s.yaspeller.constant

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class YaSpellerConstantsTest {

    @Test
    fun `should return valid MAX_TEXT_LENGTH`() {
        expectThat(MAX_TEXT_LENGTH) isEqualTo 10_000
    }

    @Test
    fun `should return valid parameter names`() {
        expectThat(PARAMETER_LANGUAGES) isEqualTo "lang"
        expectThat(PARAMETER_TEXT) isEqualTo "text"
        expectThat(PARAMETER_OPTIONS) isEqualTo "options"
        expectThat(PARAMETER_FORMAT) isEqualTo "format"
    }

    @Test
    fun `should return valid BASE_URL`() {
        expectThat(BASE_URL) isEqualTo "https://speller.yandex.net/services/spellservice.json/checkText"
    }
}