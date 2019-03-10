package org.bitbucket.muhatashim.kherkin.lang.builder

import org.bitbucket.muhatashim.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.BackgroundX
import org.zarif.kherkin.lang.construct.StepX
import org.zarif.kherkin.lang.meta.BackgroundMeta

@org.bitbucket.muhatashim.kherkin.lang.KherkinDsl
class BackgroundBuilder {
    private val steps = mutableListOf<StepX>()
    var name: String? = null
    var description: String? = null

    inline fun steps(setup: SimpleStepBuilder.() -> Unit) {
        addSteps(SimpleStepBuilder().apply(setup).build())
    }

    fun addSteps(step: List<StepX>) {
        steps += step
    }

    fun build(): BackgroundX {
        return BackgroundX(
            name, description, steps, BackgroundMeta(
                name = name ?: "",
                description = description ?: "",
                keyword = "Background",
                type = "background",
                steps = steps.map { it.meta }.toMutableList()
            )
        )
    }
}