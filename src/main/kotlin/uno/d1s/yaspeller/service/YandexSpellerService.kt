package uno.d1s.yaspeller.service

import uno.d1s.yaspeller.domain.api.SpellCheckResult
import uno.d1s.yaspeller.domain.api.RequestConfiguration

internal interface YandexSpellerService {

    suspend fun checkText(
        text: String,
        configuration: RequestConfiguration = RequestConfiguration()
    ): List<SpellCheckResult>

    suspend fun checkTexts(
        texts: List<String>,
        configuration: RequestConfiguration = RequestConfiguration()
    ): List<List<SpellCheckResult>>
}