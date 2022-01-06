[![](https://jitpack.io/v/d1snin/yaspeller-kt.svg)](https://jitpack.io/#d1snin/yaspeller-kt)

# yaspeller-kt
Asynchronous Yandex.Speller API wrapper for Kotlin/JVM.

### Installation
```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

implementation("com.github.d1snin:yaspeller-kt:$yaspellerVersion")
```

### Example usage
```kotlin
suspend fun main() {
    val word = "hawdwawe"
    val suggestion = "hawdwawe".spellingSuggestion() // first spelling suggestions for the first word
    println(suggestion) // hardware
}
```
