package org.zarif.kherkin.lang.script

import org.zarif.kherkin.lang.builder.*
import org.zarif.kherkin.lang.definition.`duck enters the following credentials`
import org.zarif.kherkin.lang.definition.`duck is logged in`
import org.zarif.kherkin.lang.definition.`duck is on sign in page`
import org.zarif.kherkin.lang.definition.`duck sees the homepage`

val backgroundTest =
    Feature {
        name = "Feature to test background"
        Background {
            name = "Background name"
            steps {
                Given the `duck is on sign in page`()
            }
        }
        Scenario {
            name = "Scenario 1"
            steps {
                When the `duck enters the following credentials`(
                    username = "user",
                    password = "pass"
                )
                Then the `duck is logged in`()
                And the `duck sees the homepage`()
            }
        }
        Scenario {
            name = "Scenario 2"
            steps {
                When the `duck enters the following credentials`(
                    username = "user2",
                    password = "pass2"
                )
                Then the `duck is logged in`()
                And the `duck sees the homepage`()
            }
        }
    }