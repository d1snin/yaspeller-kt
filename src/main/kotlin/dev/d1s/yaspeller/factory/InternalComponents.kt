package dev.d1s.yaspeller.factory

import com.google.gson.Gson
import dev.d1s.yaspeller.dsl.RequestConfigurationDsl
import dev.d1s.yaspeller.service.YandexSpellerService
import dev.d1s.yaspeller.service.impl.YandexSpellerServiceImpl

internal fun yandexSpellerService(): YandexSpellerService = YandexSpellerServiceImpl()
internal fun defaultRequestConfiguration() = RequestConfigurationDsl()
internal fun gson() = Gson()