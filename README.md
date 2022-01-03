# yaspeller-kt
Asynchronous Yandex.Speller API wrapper for Kotlin/JVM.

### Installation
```groovy
repositories {
    maven { 
        url 'https://jitpack.io' 
    }
}

implementation 'com.github.d1snin:yaspeller-kt:0.1.1-beta.0'
```

### Example usage
```kotlin
suspend fun main() {
    val word = "hawdwawe"
    val suggestions = "hawdwawe".spellingSuggestion()
    println(suggestions[0]) // the first suggestion: hardware
}
```
