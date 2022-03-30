package dev.d1s.yaspeller.impl

import dev.d1s.teabag.testing.assertDoesNotThrowBlocking
import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.yaspeller.factory.yandexSpellerService
import dev.d1s.yaspeller.testUtil.mockRequestConfigurationDslFun
import dev.d1s.yaspeller.testUtil.mockTextSpellCheckResult
import dev.d1s.yaspeller.testUtil.mockYandexSpellerService
import io.mockk.*
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class YandexSpellerImplTest {

    private val yandexSpellerService = mockYandexSpellerService
    private val textSpellCheckResult = mockTextSpellCheckResult
    private val requestConfigurationDsl = mockRequestConfigurationDslFun

    @BeforeTest
    fun setUp() {
        coEvery {
            yandexSpellerService.checkText(VALID_STUB)
        } returns textSpellCheckResult
    }

    @Test
    fun `should check spelling and return valid result`() {
        this.newInstanceWithMockedDeps {
            val result = assertDoesNotThrowBlocking {
                it.checkSpelling(VALID_STUB)
            }

            coVerify {
                yandexSpellerService.checkText(VALID_STUB)
            }

            expectThat(result) isEqualTo textSpellCheckResult
        }
    }

    @Test
    fun `should update configuration`() {
        this.newInstanceWithMockedDeps {
            assertDoesNotThrowBlocking {
                it.updateConfiguration(requestConfigurationDsl)
            }

            verify {
                yandexSpellerService.updateConfiguration(requestConfigurationDsl)
            }
        }
    }

    private fun newInstanceWithMockedDeps(block: (YandexSpellerImpl) -> Unit) {
        mockkStatic("dev.d1s.yaspeller.factory.InternalComponentsKt") {
            every {
                yandexSpellerService()
            } returns yandexSpellerService

            block(YandexSpellerImpl())
        }
    }
}