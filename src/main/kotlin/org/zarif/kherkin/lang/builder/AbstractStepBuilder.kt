package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.StepX

@KherkinDsl
abstract class AbstractStepBuilder {
    protected val steps = mutableListOf<StepX>()

    infix fun StepType.the(step: StepX) {
        steps += step
    }

    fun iterate(data: List<Map<String, *>>, setup: IterationBuilder.() -> Unit) {
        require(!data.isEmpty()) { "There must be at least one item in the provided data argument." }

        data.forEach { datum ->
            IterationBuilder(datum).also {
                it.setup()
                steps += it.build()
            }
        }
    }

    open fun build(): List<StepX> {
        return steps
    }
}

fun step(execution: () -> Unit): StepX {
    return StepX(execution)
}

open class StepType
object Given : StepType()
object When : StepType()
object Then : StepType()
object And : StepType()
object But : StepType()
