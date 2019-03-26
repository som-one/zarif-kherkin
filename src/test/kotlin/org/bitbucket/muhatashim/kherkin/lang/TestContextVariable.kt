package org.bitbucket.muhatashim.kherkin.lang

import io.kotlintest.specs.StringSpec
import org.bitbucket.muhatashim.kherkin.lang.script.contextVariables

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