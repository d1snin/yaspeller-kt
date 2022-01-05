package uno.d1s.yaspeller.util

internal fun String.setParameter(placeholder: String, parameter: String): String =
    this.replace("{$placeholder}", parameter)