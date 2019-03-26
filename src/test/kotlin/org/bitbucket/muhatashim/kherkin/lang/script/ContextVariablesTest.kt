package org.bitbucket.muhatashim.kherkin.lang.script

import org.bitbucket.muhatashim.kherkin.lang.builder.*
import org.bitbucket.muhatashim.kherkin.lang.definition.*

val contextVariables =
    Feature {
        name = "Feature to context variables"
        Scenario {
            name = "Scenario 1"
            steps {
                Given the `duck has done something cool`()
                Then the `duck verifies that it has done something cool`()
                Then the `duck verifies that it has not done something cool 2`()
                When the `duck has done something cool 2`()
                Then the `duck verifies that it has done something cool 2`()
            }
        }
        Scenario {
            name = "Scenario 2"
            steps {
                Given the `duck verifies that it has not done something cool`()
                And the `duck verifies that it has not done something cool 2`()
            }
        }
    }