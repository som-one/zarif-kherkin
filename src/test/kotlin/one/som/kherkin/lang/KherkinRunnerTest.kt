package one.som.kherkin.lang

import io.kotlintest.specs.StringSpec
import one.som.kherkin.lang.execution.builder.flow
import one.som.kherkin.lang.script.backgroundTest
import one.som.kherkin.lang.script.exampleTest
import one.som.kherkin.lang.script.iterationTest
import one.som.kherkin.lang.script.parallelTest1

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

