package uno.d1s.yaspeller.dsl

import uno.d1s.yaspeller.domain.api.Language
import uno.d1s.yaspeller.dsl.marker.YaSpellerDslMarker

@YaSpellerDslMarker
class LanguagesConfigurationDsl {
    internal val languageSet = mutableSetOf<Language>()

    fun russian() {
        languageSet.add(Language.RUSSIAN)
    }

    fun ukrainian() {
        languageSet.add(Language.UKRAINIAN)
    }

    fun english() {
        languageSet.add(Language.ENGLISH)
    }
}