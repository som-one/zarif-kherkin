package one.som.kherkin.lang

import io.kotlintest.specs.StringSpec
import one.som.kherkin.lang.script.contextVariables

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 2/28/2019
 * Time: 12:31 AM
 */
class TestContextVariable : StringSpec() {

    init {
        "verify context variables" {
            contextVariables()
        }
    }
}
