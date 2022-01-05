package uno.d1s.yaspeller.domain.internal

import uno.d1s.yaspeller.domain.api.WordSpellCheckResult

internal class InternalSpellCheckResult(
    val code: Int,
    val pos: Int,
    val row: Int,
    val col: Int,
    val len: Int,
    val word: String,
    val s: List<String>
) {
    fun toApi() = WordSpellCheckResult(pos, row, col, len, word, s)
}