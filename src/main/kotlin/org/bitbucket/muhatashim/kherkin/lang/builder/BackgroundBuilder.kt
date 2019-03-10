package org.bitbucket.muhatashim.kherkin.lang.builder

import org.bitbucket.muhatashim.kherkin.lang.KherkinDsl
import org.bitbucket.muhatashim.kherkin.lang.construct.BackgroundX
import org.bitbucket.muhatashim.kherkin.lang.construct.StepX
import org.bitbucket.muhatashim.kherkin.lang.meta.BackgroundMeta

@KherkinDsl
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