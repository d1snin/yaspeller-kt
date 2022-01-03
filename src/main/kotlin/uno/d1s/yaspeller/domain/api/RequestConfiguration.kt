package uno.d1s.yaspeller.domain.api

data class RequestConfiguration(
    var languages: List<Language> = listOf(Language.RUSSIAN, Language.ENGLISH),
    var options: List<Option> = listOf(),
    var format: Format = Format.PLAIN_TEXT
)