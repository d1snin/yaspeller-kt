package dev.d1s.yaspeller.testUtil

import dev.d1s.teabag.testing.constant.VALID_STUB
import dev.d1s.yaspeller.domain.result.TextSpellCheckResult
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl
import dev.d1s.yaspeller.factory.defaultRequestConfiguration
import dev.d1s.yaspeller.service.YandexSpellerService
import io.mockk.mockk
import io.mockk.spyk

internal val mockYandexSpellerService = mockk<YandexSpellerService>(relaxUnitFun = true)

internal val mockTextSpellCheckResult = TextSpellCheckResult(
    true,
    0,
    listOf(),
    listOf(),
    listOf(),
    VALID_STUB
)

internal val spyRequestConfigurationDsl = spyk(defaultRequestConfiguration())
internal val mockRequestConfigurationDslFun: RequestConfigurationDsl.() -> Unit = {}