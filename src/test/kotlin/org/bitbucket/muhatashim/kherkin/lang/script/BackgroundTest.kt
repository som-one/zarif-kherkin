package org.bitbucket.muhatashim.kherkin.lang.script

import org.bitbucket.muhatashim.kherkin.lang.builder.Feature
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck enters the following credentials`
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck is logged in`
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck is on sign in page`
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck sees the homepage`

val backgroundTest =
    Feature("Feature to test background") {
        Background {
            name = "Background name"
            steps {
                Given the `duck is on sign in page`()
            }
        }
        fScenario("Scenario 1") {
            steps {
                When the `duck enters the following credentials`(
                    username = "user",
                    password = "pass"
                )
                Then the `duck is logged in`()
                And the `duck sees the homepage`()
            }
        }
        Scenario("Scenario 2") {
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
