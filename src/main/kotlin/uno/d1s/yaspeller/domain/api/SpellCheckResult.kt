package uno.d1s.yaspeller.domain.api

data class SpellCheckResult(
    val position: Int,
    val row: Int,
    val column: Int,
    val length: Int,
    val word: String,
    val suggestions: List<String>
) {

    val firstSuggestion get() = suggestions.first()
}