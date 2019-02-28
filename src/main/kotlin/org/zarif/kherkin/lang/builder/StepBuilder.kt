package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.construct.StepX

open class StepBuilder {
    private val steps = mutableListOf<StepX>()

    infix fun StepType.the(step: StepX) {
        steps += step
    }

    fun build(): List<StepX>{
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
