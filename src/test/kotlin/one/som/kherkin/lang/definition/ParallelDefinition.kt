package one.som.kherkin.lang.definition

import kotlinx.coroutines.delay
import one.som.kherkin.lang.builder.step

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/30/2019
 * Time: 1:06 AM
 */
fun `duck waits 1 second`() = step {
    delay(1000)
    log("Waited")
}
