package uno.d1s.yaspeller.domain.api

data class TextSpellCheckResult(
    val isValid: Boolean,
    val totalMisspelledWords: Int,
    val misspelledWords: List<String>,
    val improvedWords: List<String>,
    val wordSpellCheckResults: List<WordSpellCheckResult>,
    val improvedText: String
)