package dev.d1s.yaspeller.integration

import dev.d1s.yaspeller.factory.yandexSpeller
import kotlinx.coroutines.runBlocking
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class ApiTest {

    @Test
    fun `should check spelling`() {
        val speller = yandexSpeller {
            languages {
                english()
            }
        }

        runBlocking {
            expectThat("hardware") isEqualTo
                    speller.checkSpelling("hawdwarle").improvedText
        }
    }
}
