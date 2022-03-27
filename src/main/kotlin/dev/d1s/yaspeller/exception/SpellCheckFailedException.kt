package dev.d1s.yaspeller.exception

public class SpellCheckFailedException(override val message: String) : RuntimeException(message)