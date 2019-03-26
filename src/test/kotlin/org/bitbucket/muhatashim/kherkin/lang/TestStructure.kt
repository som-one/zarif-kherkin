package org.bitbucket.muhatashim.kherkin.lang

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec
import org.bitbucket.muhatashim.kherkin.lang.script.backgroundTest
import org.bitbucket.muhatashim.kherkin.lang.script.exampleTest
import org.bitbucket.muhatashim.kherkin.lang.script.iterationTest

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 2/28/2019
 * Time: 12:31 AM
 */
class TestStructure : StringSpec() {

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

        "verify examples" {
            exampleTest.scenarios shouldHaveSize 4
            exampleTest.scenarios.forEach { it.steps shouldHaveSize 3 }
            exampleTest.background shouldNotBe null
            exampleTest()
        }
    }
}