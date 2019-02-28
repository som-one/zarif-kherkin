package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.construct.BackgroundX
import org.zarif.kherkin.lang.construct.StepX

class BackgroundBuilder {
    private val steps = mutableListOf<StepX>()
    var name: String? = null
    var description: String? = null

    inline fun steps(setup: StepBuilder.() -> Unit) {
        StepBuilder().also {
            it.setup()
            addSteps(it.build())
        }
    }

    fun addSteps(step: List<StepX>) {
        steps += step
    }

    fun build(): BackgroundX {
        return BackgroundX(name, description, steps)
    }
}