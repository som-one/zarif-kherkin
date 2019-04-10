package org.bitbucket.muhatashim.kherkin.lang.builder


@KherkinDsl
class SimpleScenarioBuilder(callSite: StackTraceElement) : AbstractScenarioBuilder(callSite) {

    inline fun steps(setup: SimpleStepBuilder.() -> Unit) {
        addSteps(SimpleStepBuilder().apply(setup).build())
    }
}