[![](https://jitpack.io/v/d1snin/yaspeller-kt.svg)](https://jitpack.io/#d1snin/yaspeller-kt)

# yaspeller-kt
Asynchronous Yandex.Speller API wrapper for Kotlin/JVM.

### Installation
```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("dev.d1s:yaspeller-kt:$yaspellerVersion")
}
```

### Example usage
```kotlin
private val speller = yandexSpeller()

suspend fun main() {
    val word = "hawdwawe"
    val suggestion = speller.checkSpelling(word).improvedText
    println(suggestion) // hardware
}
```
