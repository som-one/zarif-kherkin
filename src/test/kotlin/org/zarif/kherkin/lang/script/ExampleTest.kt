package org.zarif.kherkin.lang.script

import org.zarif.kherkin.lang.builder.*
import org.zarif.kherkin.lang.definition.`duck enters the following credentials`
import org.zarif.kherkin.lang.definition.`duck is logged in`
import org.zarif.kherkin.lang.definition.`duck is on sign in page`
import org.zarif.kherkin.lang.definition.`duck sees the homepage`

val exampleTest =
    Feature {
        name = "Feature to test examples"
        Background {
            steps {
                Given the `duck is on sign in page`()
            }
        }
        Example(csv("/data/user-pass.csv")) {
            name = "Test examples"
            steps {
                When the `duck enters the following credentials`(
                    username = "user" from examples,
                    password = "pass" from examples
                )
                Then the `duck is logged in`()
                And the `duck sees the homepage`()
            }
        }
    }