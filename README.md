 # Kherkin
 Kerkin is a Kotlin based DSL written with testers, developers, and regular people outside of a team in mind. The inspiration for this project comes from the Gherkin syntax and the Cucumber framework. Howver, there is a huge problem that both of these technologies face, and that is the lack of customizability. This usually leads to long delays in delivering feature files due to not following standards and rewriting code multiple times. Kherkin is designed so that everyone gets the benefit of highly readable feature files, faster development times, and full customizability.
 
 # Getting Started
//TODO

# Examples
```Kotlin
Feature {
    name = "Some Feature"
    description = "This feature does a lot of cool stuff"
    Background {
        name = "Background name"
        steps {
            Given the `duck is on sign in page`
        }
    }
    Scenario {
        name = "Scenario name 1"
        description = "This scenario does the really cool stuff"
        steps {
            When the `duck enters the following credentials`(
                username = "user",
                password = "pass"
            )
            Then the `duck is logged in`
            And the `duck sees the homepage`
        }
    }
    Scenario {
        name = "Scenario name 2"
        description = "This scenario does some cool stuff"
        steps {
            When the `duck enters the following credentials`(
                username = "user2",
                password = "pass2"
            )
            Then the `duck is logged in`
            And the `duck sees the homepage`
        }
    }
}
```