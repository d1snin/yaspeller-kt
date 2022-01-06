[![](https://jitpack.io/v/d1snin/yaspeller-kt.svg)](https://jitpack.io/#d1snin/yaspeller-kt)

# yaspeller-kt
Asynchronous Yandex.Speller API wrapper for Kotlin/JVM.

### Installation
```groovy
repositories {
    maven { 
        url 'https://jitpack.io' 
    }
}

implementation 'com.github.d1snin:yaspeller-kt:0.2.1-beta.2'
```

### Example usage
```kotlin
suspend fun main() {
    val word = "hawdwawe"
    val suggestion = word.checkSpelling().improvedText
    println(suggestion) // hardware
}
```
