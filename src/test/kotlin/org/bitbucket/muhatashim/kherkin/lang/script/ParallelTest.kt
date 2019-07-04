package org.bitbucket.muhatashim.kherkin.lang.script

import org.bitbucket.muhatashim.kherkin.lang.builder.Feature
import org.bitbucket.muhatashim.kherkin.lang.definition.`duck waits 1 second`

val parallelTest1 =
    Feature("Feature to context variables") {
        Scenario("Scenario 1") {
            steps {
                Given the `duck waits 1 second`()
            }
        }
    }
