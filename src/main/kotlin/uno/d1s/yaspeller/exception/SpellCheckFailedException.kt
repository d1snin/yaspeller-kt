package dev.d1s.yaspeller.exception

class SpellCheckFailedException(override val message: String) : RuntimeException(message)