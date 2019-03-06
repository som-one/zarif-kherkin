package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.BackgroundX
import org.zarif.kherkin.lang.construct.StepX

@KherkinDsl
class BackgroundBuilder {
    private val steps = mutableListOf<StepX>()
    var name: String? = null
    var description: String? = null

    inline fun steps(setup: StepBuilder.() -> Unit) {
        addSteps(StepBuilder().apply(setup).build())
    }

    fun addSteps(step: List<StepX>) {
        steps += step
    }

    fun build(): BackgroundX {
        return BackgroundX(name, description, steps)
    }
}