package org.zarif.kherkin.lang

import io.kotlintest.TestCaseConfig
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import org.zarif.kherkin.lang.script.backgroundTest
import org.zarif.kherkin.lang.script.iterationTest

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 2/28/2019
 * Time: 12:31 AM
 */
class TestBackground : StringSpec() {
    override val defaultTestCaseConfig: TestCaseConfig = TestCaseConfig(threads = 3)

    init {
        "verify background" {
            backgroundTest.scenarios shouldHaveSize 2
            backgroundTest.background shouldNotBe null
            backgroundTest()
        }

        "verify iterations" {
            iterationTest.scenarios[0].steps.size shouldBe 4 * 3
            iterationTest.background shouldNotBe null
            iterationTest()
        }
    }
}