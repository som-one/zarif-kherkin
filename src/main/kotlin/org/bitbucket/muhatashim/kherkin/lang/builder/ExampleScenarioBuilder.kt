package org.bitbucket.muhatashim.kherkin.lang.builder


@KherkinDsl
class ExampleScenarioBuilder(var example: Map<String, *>, callSite: StackTraceElement) :
    AbstractScenarioBuilder(callSite) {

    fun steps(setup: ExampleStepBuilder.() -> Unit) {
        addSteps(ExampleStepBuilder(example).apply(setup).build())
    }
}