package org.zarif.kherkin.lang

import io.kotlintest.specs.StringSpec
import org.zarif.kherkin.lang.script.backgroundTest
import org.zarif.kherkin.lang.script.exampleTest
import org.zarif.kherkin.lang.script.iterationTest

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/9/2019
 * Time: 8:18 PM
 */
class KherkinRunnerTest : StringSpec() {
    init {
        "verify runner works"{
            KherkinRunner(backgroundTest, exampleTest, iterationTest).invokeAndLog()
        }
    }
}

