package dev.d1s.yaspeller.domain

public enum class Option(internal val optionValue: Int) {
    // FIND_REPEAT_WORDS is not available, because this option is used for HTML responses.
    IGNORE_DIGITS(2),
    IGNORE_URLS(4),
    IGNORE_CAPITALIZATION(512)
}