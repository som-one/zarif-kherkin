package org.bitbucket.muhatashim.kherkin.lang

import io.kotlintest.specs.StringSpec
import org.bitbucket.muhatashim.kherkin.lang.execution.builder.flow
import org.bitbucket.muhatashim.kherkin.lang.script.backgroundTest
import org.bitbucket.muhatashim.kherkin.lang.script.exampleTest
import org.bitbucket.muhatashim.kherkin.lang.script.iterationTest
import org.bitbucket.muhatashim.kherkin.lang.script.parallelTest1

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

        "verify runner can run in parallel"{
            KherkinRunner(
                flow {
                    parallel {
                        for (i in 0 until 10) {
                            -parallelTest1
                        }
                        serial {
                            -parallelTest1
                            -parallelTest1
                        }
                    }
                    serial {
                        -parallelTest1
                        -parallelTest1
                        -parallelTest1

                        parallel {
                            -parallelTest1
                            -parallelTest1
                            -parallelTest1
                            -parallelTest1
                        }
                    }
                }
            ).invoke()
        }
    }
}

