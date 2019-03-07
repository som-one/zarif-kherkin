package org.zarif.kherkin.lang.script

import org.zarif.kherkin.lang.builder.*
import org.zarif.kherkin.lang.definition.`duck enters the following credentials`
import org.zarif.kherkin.lang.definition.`duck is logged in`
import org.zarif.kherkin.lang.definition.`duck is on sign in page`
import org.zarif.kherkin.lang.definition.`duck sees the homepage`

val iterationTest =
    Feature {
        name = "Feature to test iterations"
        Background {
            steps {
                Given the `duck is on sign in page`
            }
        }
        Scenario {
            name = "Test iterations"
            steps {
                iterate(csv("/data/user-pass.csv")) {
                    When the `duck enters the following credentials`(
                        username = "user" from iteration,
                        password = "pass" from iteration
                    )
                    Then the `duck is logged in`
                    And the `duck sees the homepage`
                }
            }
        }
    }