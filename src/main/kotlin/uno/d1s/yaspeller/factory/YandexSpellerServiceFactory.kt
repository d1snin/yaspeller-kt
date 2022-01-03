package uno.d1s.yaspeller.factory

import uno.d1s.yaspeller.service.YandexSpellerService
import uno.d1s.yaspeller.service.impl.YandexSpellerServiceImpl

internal fun yandexSpeller(): YandexSpellerService = YandexSpellerServiceImpl