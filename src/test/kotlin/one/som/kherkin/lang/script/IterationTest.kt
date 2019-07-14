package one.som.kherkin.lang.script

import one.som.kherkin.lang.builder.*
import one.som.kherkin.lang.definition.`duck enters the following credentials`
import one.som.kherkin.lang.definition.`duck is logged in`
import one.som.kherkin.lang.definition.`duck is on sign in page`
import one.som.kherkin.lang.definition.`duck sees the homepage`

val iterationTest =
    Feature("Feature to test iterations") {
        Background {
            steps {
                Given the `duck is on sign in page`()
            }
        }
        Scenario("Test iterations") {
            steps {
                iterate(csv("/data/user-pass.csv")) {
                    When the `duck enters the following credentials`(
                        username = "user" from iteration,
                        password = "pass" from iteration
                    )
                    Then the `duck is logged in`()
                }
                And the `duck sees the homepage`()
            }
        }
    }
