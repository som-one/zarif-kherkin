package one.som.kherkin.lang.script

import one.som.kherkin.lang.builder.*
import one.som.kherkin.lang.definition.*

val contextVariables =
    Feature("Feature to context variables") {
        Scenario("Scenario 1") {
            steps {
                Given the `duck has done something cool`()
                Then the `duck verifies that it has done something cool`()
                Then the `duck verifies that it has not done something cool 2`()
                When the `duck has done something cool 2`()
                Then the `duck verifies that it has done something cool 2`()
            }
        }
        Scenario("Scenario 2") {
            steps {
                Given the `duck verifies that it has not done something cool`()
                And the `duck verifies that it has not done something cool 2`()
            }
        }
    }
