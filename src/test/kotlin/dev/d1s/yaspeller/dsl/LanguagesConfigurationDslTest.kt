package dev.d1s.yaspeller.dsl

import dev.d1s.yaspeller.domain.Language
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isEmpty
import kotlin.test.Test

internal class LanguagesConfigurationDslTest {

    @Test
    fun `should add languages`() {
        val dsl = LanguagesConfigurationDsl()

        expectThat(dsl.languageSet).isEmpty()

        dsl.russian()

        expectThat(dsl.languageSet)
            .containsExactly(Language.RUSSIAN)

        dsl.ukrainian()

        expectThat(dsl.languageSet)
            .containsExactly(Language.RUSSIAN, Language.UKRAINIAN)

        dsl.english()

        expectThat(dsl.languageSet).containsExactly(
            Language.RUSSIAN,
            Language.UKRAINIAN,
            Language.ENGLISH
        )
    }
}