package dev.d1s.yaspeller.configuration

import strikt.api.expectThat
import strikt.assertions.isNull
import kotlin.test.Test

internal class InternalRequestConfigurationTest {

    @Test
    fun `should return null as a default value for all fields`() {
        val configuration = InternalRequestConfiguration()

        expectThat(configuration.languages).isNull()
        expectThat(configuration.options).isNull()
        expectThat(configuration.format).isNull()
    }
}