package one.som.kherkin.lang.script

import one.som.kherkin.lang.builder.Feature
import one.som.kherkin.lang.definition.`duck waits 1 second`

val parallelTest1 =
    Feature("Feature to context variables") {
        Scenario("Scenario 1") {
            steps {
                Given the `duck waits 1 second`()
            }
        }
    }
