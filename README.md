[![](https://jitpack.io/v/d1snin/yaspeller-kt.svg)](https://jitpack.io/#d1snin/yaspeller-kt)

# yaspeller-kt
Asynchronous Yandex.Speller API wrapper for Kotlin/JVM.

### Installation
```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

implementation("dev.d1s:yaspeller-kt:$yaspellerVersion")
```

### Example usage
```kotlin
suspend fun main() {
    val word = "hawdwawe"
    val suggestion = word.checkSpelling().improvedText
    println(suggestion) // hardware
}
```
