package dev.d1s.yaspeller.service

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Headers
import com.google.gson.Gson
import dev.d1s.teabag.testing.assertDoesNotThrowBlocking
import dev.d1s.teabag.testing.assertThrowsBlocking
import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.yaspeller.constant.BASE_URL
import dev.d1s.yaspeller.constant.MAX_TEXT_LENGTH
import dev.d1s.yaspeller.domain.result.WordSpellCheckResult
import dev.d1s.yaspeller.exception.SpellCheckFailedException
import dev.d1s.yaspeller.factory.defaultRequestConfiguration
import dev.d1s.yaspeller.factory.gson
import dev.d1s.yaspeller.service.impl.YandexSpellerServiceImpl
import dev.d1s.yaspeller.testUtil.spyRequestConfigurationDsl
import io.mockk.*
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import java.lang.reflect.Type
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class YandexSpellerServiceImplTest {

    private val requestConfiguration = spyRequestConfigurationDsl
    private val gson = mockk<Gson>()

    private val realGson = gson()

    private val mockResult = mockk<com.github.kittinunf.result.Result<ByteArray, FuelError>>()
    private val mockError = mockk<FuelError>(relaxed = true)

    private val mockWordSpellCheckResults = listOf(
        WordSpellCheckResult(
            0,
            0,
            0,
            0,
            VALID_STUB,
            listOf(VALID_STUB)
        )
    )

    @BeforeTest
    fun setUp() {
        every {
            mockResult.component1() // bytes
        } returns realGson.toJson(mockWordSpellCheckResults)
            .toByteArray()

        every {
            mockResult.component2() // error
        } returns null

        every {
            gson.fromJson<List<WordSpellCheckResult>>(any<String>(), any<Type>())
        } returns mockWordSpellCheckResults
    }

    @Test
    fun `should check text`() {
        this.newInstanceWithMockedDeps {
            val result = assertDoesNotThrowBlocking {
                it.checkText(VALID_STUB)
            }

            verify {
                requestConfiguration.toInternalConfiguration()
            }

            verify {
                Fuel.get(BASE_URL, any())
                    .header(Headers.ACCEPT to "application/json")
                    .response()
                    .component3() // result
            }

            verify {
                mockResult.component2() // error
            }

            verify {
                mockResult.component1() // bytes
            }

            verify {
                gson.fromJson<List<WordSpellCheckResult>>(any<String>(), any<Type>())
            }

            expectThat(result.isValid).isFalse()
            expectThat(result.totalMisspelledWords) isEqualTo 1
            expectThat(result.misspelledWords) isEqualTo listOf(VALID_STUB)
            expectThat(result.improvedWords) isEqualTo listOf(VALID_STUB)
            expectThat(result.wordSpellCheckResults) isEqualTo mockWordSpellCheckResults
            expectThat(result.improvedText) isEqualTo VALID_STUB
        }
    }

    @Test
    fun `should throw SpellCheckFailedException is the given text is too long`() {
        this.newInstanceWithMockedDeps {
            val exception = assertThrowsBlocking<SpellCheckFailedException> {
                it.checkText(
                    VALID_STUB.repeat(MAX_TEXT_LENGTH + 1)
                )
            }

            expectThat(exception.message) isEqualTo
                    "Provided text is too long. Should be less than $MAX_TEXT_LENGTH"
        }
    }

    @Test
    fun `should throw SpellCheckFailedException if the request failed`() {
        this.newInstanceWithMockedDeps {
            every {
                mockResult.component2() // error
            } returns mockError

            val exception = assertThrowsBlocking<SpellCheckFailedException> {
                it.checkText(VALID_STUB)
            }

            verify {
                mockError.response
            }

            expectThat(exception.message) isEqualTo
                    mockError.response.toString()
        }
    }

    private fun newInstanceWithMockedDeps(block: (YandexSpellerServiceImpl) -> Unit) {
        mockkStatic("dev.d1s.yaspeller.factory.InternalComponentsKt") {
            every {
                defaultRequestConfiguration()
            } returns requestConfiguration

            every {
                gson()
            } returns gson

            mockkObject(Fuel) {
                every {
                    Fuel.get(BASE_URL, any())
                        .header(Headers.ACCEPT to "application/json")
                        .response()
                        .component3() // result
                } returns mockResult

                block(YandexSpellerServiceImpl())
            }
        }
    }
}