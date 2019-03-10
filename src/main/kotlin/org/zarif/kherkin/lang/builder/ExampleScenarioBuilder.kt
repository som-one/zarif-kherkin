package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl


@KherkinDsl
class ExampleScenarioBuilder(var example: Map<String, *>, callSite: StackTraceElement) :
    AbstractScenarioBuilder(callSite) {

    fun steps(setup: ExampleStepBuilder.() -> Unit) {
        addSteps(ExampleStepBuilder(example).apply(setup).build())
    }
}