package dev.d1s.yaspeller.constant

internal const val MAX_TEXT_LENGTH = 10000

internal const val PARAMETER_LANGUAGES = "lang"
internal const val PARAMETER_TEXT = "text"
internal const val PARAMETER_OPTIONS = "options"
internal const val PARAMETER_FORMAT = "format"

// using the json format.
internal const val BASE_URL =
    "https://speller.yandex.net/services/spellservice.json/checkText" +
            "?$PARAMETER_LANGUAGES={$PARAMETER_LANGUAGES}" +
            "&$PARAMETER_TEXT={$PARAMETER_TEXT}" +
            "&$PARAMETER_OPTIONS={$PARAMETER_OPTIONS}" +
            "&$PARAMETER_FORMAT={$PARAMETER_FORMAT}"