![Bitbucket Pipelines branch](https://img.shields.io/bitbucket/pipelines/Muhatashim/zarif-kherkin/master.svg?label=master)
![Maven Central](https://img.shields.io/maven-central/v/org.bitbucket.muhatashim/zarif-kherkin.svg?label=zarif-kherkin)
      
# Kherkin
Kerkin is a Kotlin based DSL written with testers, developers, and regular people outside of a team in mind. The inspiration for this project comes from the Gherkin syntax and the Cucumber framework. Howver, there is a huge problem that both of these technologies face, and that is the lack of customizability. This usually leads to long delays in delivering feature files due to not following standards and rewriting code multiple times. Kherkin is designed so that everyone gets the benefit of highly readable feature files, faster development times, and full customizability.
 
# Getting Started
Gradle:
```groovy
testCompile "org.bitbucket.muhatashim:zarif-kherkin:1.2.0-RELEASE"
```
Maven:
```xml
<dependency>
  <groupId>org.bitbucket.muhatashim</groupId>
  <artifactId>zarif-kherkin</artifactId>
  <version>1.2.0-RELEASE</version>
</dependency>
```

# Examples
1. [ Simple background and two scenarios ](#ex1)
1. [ Looping a full scenario ](#ex2)
1. [ Context Variables ](#ex3)
1. [ Looping only part of the scenario ](#ex4)
1. [ Running features in parallel ](#ex5)

<a name="ex1"></a>
## Simple background and two scenarios
```Kotlin
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
```

`BasicDefinition.kt`
```Kotlin
val logger = Logger.getGlobal()

fun log(s: String) = logger.log(Level.INFO, s)

fun `duck is on sign in page`() = step {
    log(">>> Duck navigated to the sign in page")
}

fun `duck enters the following credentials`(username: String, password: String) =
    step("username" to username, "password" to password) {
        log(">>> Duck entered $username, $password")
    }

fun `duck is logged in`() = step {
    log(">>> Duck is logged in")
}

fun `duck sees the homepage`() = step {
    log(">>> Duck sees the homepage")
}
```
`KherkinRunnerTest.kt`
```Kotlin
KherkinRunner(backgroundTest).invokeAndLog()
```
<a name="ex2"></a>
## Looping a full scenario
This example uses the Example keyword and dynamically loads the iteration data using the csv function.
```Kotlin
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
```
`user-pass.csv`
```CSV
user ,pass
user1,pass1
user2,pass2
user3,pass3
user4,pass4
```


<a name="ex3"></a>
## Context Variables
```Kotlin
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
```

`ContextVariablesTest.kt`
```Kotlin
val cool1 = Key<String>()
val cool2 = Key<String>()

fun `duck has done something cool`() = step {
    putContext(cool1, "Cool1")
}

fun `duck has done something cool 2`() = step {
    putContext(cool2, "Cool2")
}

fun `duck verifies that it has done something cool`() = step {
    getContext(cool1) shouldBe "Cool1"
}

fun `duck verifies that it has done something cool 2`() = step {
    getContext(cool2) shouldBe "Cool2"
}

fun `duck verifies that it has not done something cool`() = step {
    getContext(cool1) shouldNotBe "Cool1"
}

fun `duck verifies that it has not done something cool 2`() = step {
    getContext(cool2) shouldNotBe "Cool2"
}
```

<a name="ex4"></a>
## Looping only part of the scenario
```Kotlin
Feature {
    name = "Feature to test iterations"
    Background {
        steps {
            Given the `duck is on sign in page`()
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
                Then the `duck is logged in`()
            }
            And the `duck sees the homepage`()
        }
    }
}
```

<a name="ex5"></a>
## Running features in parallel
```Kotlin
val parallelTest1 =
    Feature {
        name = "Feature to context variables"
        Scenario {
            name = "Scenario 1"
            steps {
                Given the `duck waits 1 second`()
            }
        }
    }
```
`ParallelDefinition.kt`
``` Kotlin
fun `duck waits 1 second`() = step {
    delay(1000)
    log("Waited")
}
```
`KherkinRunnerTest.kt`
```Kotlin
KherkinRunner(
    flow {
        parallel {
            for (i in 0 until 10) {
                -parallelTest1
            }
            serial {
                -parallelTest1
                -parallelTest1
            }
        }
        serial {
            -parallelTest1
            -parallelTest1
            -parallelTest1

            parallel {
                -parallelTest1
                -parallelTest1
                -parallelTest1
                -parallelTest1
            }
        }
    }
).invoke()
```
