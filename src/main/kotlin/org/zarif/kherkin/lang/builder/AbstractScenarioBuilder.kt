package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.ScenarioX
import org.zarif.kherkin.lang.construct.StepX


@KherkinDsl
abstract class AbstractScenarioBuilder {
    private val steps = mutableListOf<StepX>()
    var name: String? = null
    var description: String? = null

    fun addSteps(step: List<StepX>) {
        steps += step
    }

    fun build(): ScenarioX {
        requireNotNull(name) { "Scenario name cannot be null!" }
        return ScenarioX(name!!, description, steps)
    }
}