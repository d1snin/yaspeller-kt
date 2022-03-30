package dev.d1s.yaspeller.dsl

import dev.d1s.yaspeller.configuration.InternalRequestConfiguration
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class RequestConfigurationDslTest {

    @Test
    fun `should return valid internal configuration`() {
        expectThat(
            RequestConfigurationDsl().toInternalConfiguration()
        ) isEqualTo InternalRequestConfiguration(
            "ru,en",
            0,
            "plain"
        )
    }
}