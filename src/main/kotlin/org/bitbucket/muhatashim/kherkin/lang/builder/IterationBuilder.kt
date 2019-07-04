package org.bitbucket.muhatashim.kherkin.lang.builder

import org.bitbucket.muhatashim.kherkin.lang.construct.StepX

class IterationBuilder(val datum: Map<String, *> = mapOf<String, Any>()) {
    private val steps = mutableListOf<StepX>()

    inline infix fun <reified R> String.from(@Suppress("UNUSED_PARAMETER") ignored: iteration): R {
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
