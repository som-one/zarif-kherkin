package org.bitbucket.muhatashim.kherkin.lang

import io.kotlintest.specs.StringSpec
import org.bitbucket.muhatashim.kherkin.lang.script.backgroundTest
import org.bitbucket.muhatashim.kherkin.lang.script.exampleTest
import org.bitbucket.muhatashim.kherkin.lang.script.iterationTest

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

