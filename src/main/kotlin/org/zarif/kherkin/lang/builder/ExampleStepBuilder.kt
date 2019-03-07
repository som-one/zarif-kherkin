package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.StepX

@KherkinDsl
class ExampleStepBuilder(val example: Map<String, *> = mapOf<String, Any>()) : AbstractStepBuilder() {
    inline infix fun <reified R> String.from(ignored: examples): R {
        val value = requireNotNull(example[this]) {
            "Could not find variable \"$this\" inside example. Current example is $example"
        }
        return cast(value) {
            "Required variable \"$this\" is set to \"$value\" but is not a type of ${R::class}"
        }
    }


    override fun build(): List<StepX> {
        return steps.map { it.copy(datum = example) }
    }
}

object examples