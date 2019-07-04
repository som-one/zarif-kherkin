package org.bitbucket.muhatashim.kherkin.lang.script

import org.bitbucket.muhatashim.kherkin.lang.builder.Feature
import org.bitbucket.muhatashim.kherkin.lang.builder.csv
import org.bitbucket.muhatashim.kherkin.lang.builder.examples
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck enters the following credentials`
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck is logged in`
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck is on sign in page`
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck sees the homepage`

val exampleTest =
    Feature("Feature to test examples") {
        Background {
            steps {
                Given the `duck is on sign in page`()
            }
        }
        Example("Test examples", csv("/data/user-pass.csv")) {
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
