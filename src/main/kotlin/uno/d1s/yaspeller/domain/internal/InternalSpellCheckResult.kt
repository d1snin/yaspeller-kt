package uno.d1s.yaspeller.domain.internal

import kotlinx.serialization.Serializable
import uno.d1s.yaspeller.domain.api.SpellCheckResult

@Serializable
data class InternalSpellCheckResult(
    val code: Int,
    val pos: Int,
    val row: Int,
    val col: Int,
    val len: Int,
    val word: String,
    val s: List<String>
) {
    fun toApi() = SpellCheckResult(pos, row, col, len, word, s)
}