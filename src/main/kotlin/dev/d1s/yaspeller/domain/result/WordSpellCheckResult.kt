package dev.d1s.yaspeller.domain.result

import com.google.gson.annotations.SerializedName

public data class WordSpellCheckResult(

    @SerializedName("pos")
    val position: Int,

    @SerializedName("row")
    val row: Int,

    @SerializedName("col")
    val column: Int,

    @SerializedName("len")
    val length: Int,

    @SerializedName("word")
    val word: String,

    @SerializedName("s")
    val suggestions: List<String>
) {

    val firstSuggestion: String get() = suggestions.first()
}