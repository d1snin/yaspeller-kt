package dev.d1s.yaspeller.exception

import dev.d1s.yaspeller.constant.MAX_TEXT_LENGTH

object TooLongTextException :
    RuntimeException("The provided text is too long. The length of the text should not exceed $MAX_TEXT_LENGTH characters.")