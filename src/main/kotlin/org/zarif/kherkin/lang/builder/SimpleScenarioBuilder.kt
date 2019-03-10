package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl


@KherkinDsl
class SimpleScenarioBuilder(callSite: StackTraceElement) : AbstractScenarioBuilder(callSite) {

    inline fun steps(setup: SimpleStepBuilder.() -> Unit) {
        addSteps(SimpleStepBuilder().apply(setup).build())
    }
}