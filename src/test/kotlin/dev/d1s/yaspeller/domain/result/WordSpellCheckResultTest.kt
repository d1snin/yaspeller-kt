package dev.d1s.yaspeller.domain.result

import dev.d1s.teabag.testing.constant.VALID_STUB
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class WordSpellCheckResultTest {

    @Test
    fun `should return first suggestion`() {
        expectThat(
            WordSpellCheckResult(
                0,
                0,
                0,
                0,
                VALID_STUB,
                listOf(VALID_STUB)
            ).firstSuggestion
        ) isEqualTo VALID_STUB
    }
}