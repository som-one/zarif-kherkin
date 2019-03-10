package org.bitbucket.muhatashim.kherkin.lang.builder

import org.bitbucket.muhatashim.kherkin.lang.KherkinDsl


@org.bitbucket.muhatashim.kherkin.lang.KherkinDsl
class SimpleScenarioBuilder(callSite: StackTraceElement) : AbstractScenarioBuilder(callSite) {

    inline fun steps(setup: SimpleStepBuilder.() -> Unit) {
        addSteps(SimpleStepBuilder().apply(setup).build())
    }
}