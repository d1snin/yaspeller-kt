package dev.d1s.yaspeller.domain.api

data class WordSpellCheckResult(
    val position: Int,
    val row: Int,
    val column: Int,
    val length: Int,
    val word: String,
    val suggestions: List<String>
) {

    val firstSuggestion get() = suggestions.first()
}