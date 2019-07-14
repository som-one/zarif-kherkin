package one.som.kherkin.lang.builder

import one.som.kherkin.lang.construct.BackgroundX
import one.som.kherkin.lang.construct.StepX
import one.som.kherkin.lang.meta.BackgroundMeta

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
