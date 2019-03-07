package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.StepX

@KherkinDsl
class IterationBuilder(val datum: Map<String, *> = mapOf<String, Any>()) {
    private val steps = mutableListOf<StepX>()

    infix fun StepType.the(step: StepX) {
        steps += step.copy(datum = this@IterationBuilder.datum)
    }

    inline infix fun <reified R> String.from(ignored: iteration): R {
        val value = requireNotNull(datum[this]) {
            "Could not find variable \"$this\" inside iteration. Current iteration is $datum"
        }
        return cast(value) {
            "Required variable \"$this\" is set to \"$value\" but is not a type of ${R::class}"
        }
    }

    fun build(): List<StepX> {
        return steps
    }
}

object iteration