package dev.d1s.yaspeller.dsl

import dev.d1s.yaspeller.domain.Language
import dev.d1s.yaspeller.dsl.marker.YaSpellerDslMarker

@YaSpellerDslMarker
public class LanguagesConfigurationDsl {

    public val languageSet: MutableSet<Language> = mutableSetOf<Language>()

    public fun russian() {
        languageSet.add(Language.RUSSIAN)
    }

    public fun ukrainian() {
        languageSet.add(Language.UKRAINIAN)
    }

    public fun english() {
        languageSet.add(Language.ENGLISH)
    }
}