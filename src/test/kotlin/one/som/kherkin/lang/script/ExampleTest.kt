package one.som.kherkin.lang.script

import one.som.kherkin.lang.builder.Feature
import one.som.kherkin.lang.builder.csv
import one.som.kherkin.lang.builder.examples
import one.som.kherkin.lang.definition.`duck enters the following credentials`
import one.som.kherkin.lang.definition.`duck is logged in`
import one.som.kherkin.lang.definition.`duck is on sign in page`
import one.som.kherkin.lang.definition.`duck sees the homepage`

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
