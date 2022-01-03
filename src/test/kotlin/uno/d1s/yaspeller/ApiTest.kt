package uno.d1s.yaspeller

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ApiTest {
    @Test
    fun `pls work`() {
        runBlocking {
            assertEquals("hardware", "hawdwarle".suggestSpellings()[0])
        }
    }
}
