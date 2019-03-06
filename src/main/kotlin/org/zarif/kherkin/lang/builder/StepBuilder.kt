package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.StepX

@KherkinDsl
class StepBuilder(val data: List<Map<String, *>> = listOf()) {
    private val steps = mutableListOf<StepX>()

    infix fun StepType.the(step: StepX) {
        steps += step
    }

    infix fun StepType.the(stepList: List<StepX>) {
        steps += stepList
    }

    infix fun iteration(setup: IterationBuilder.() -> Unit) {
        data.forEach { datum ->
            IterationBuilder(datum).also {
                it.setup()
                steps += it.build()
            }
        }
    }

    fun iteration(data: List<Map<String, *>> = listOf(), setup: IterationBuilder.() -> Unit) {
        data.forEach { datum ->
            IterationBuilder(datum).also {
                it.setup()
                steps += it.build()
            }
        }
    }

    fun build(): List<StepX> {
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
