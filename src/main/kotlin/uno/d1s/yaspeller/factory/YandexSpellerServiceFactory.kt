package dev.d1s.yaspeller.factory

import dev.d1s.yaspeller.service.YandexSpellerService
import dev.d1s.yaspeller.service.impl.YandexSpellerServiceImpl

internal fun yandexSpeller(): YandexSpellerService = YandexSpellerServiceImpl