package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.StepX
import java.text.SimpleDateFormat
import java.util.*

@KherkinDsl
class IterationBuilder(val datum: Map<String, *> = mapOf<String, Any>()) {
    private val steps = mutableListOf<StepX>()

    infix fun StepType.the(step: StepX) {
        steps += step.copy(datum = this@IterationBuilder.datum)
    }

    infix fun StepType.the(stepList: List<StepX>) {
        stepList.forEach { the(it) }
    }

    inline infix fun <reified R> String.from(ignored: iteration): R {
        val value = requireNotNull(datum[this]) { "Could not find variable \"$this\" inside data. Current data is $datum" }
        return when (R::class) {
            String::class -> value.toString()
            Integer::class -> value.toString().toInt()
            Long::class -> value.toString().toLong()
            Double::class -> value.toString().toDouble()
            Float::class -> value.toString().toFloat()
            Date::class -> SimpleDateFormat().format(value)
            else -> {
                require(value is R) { "Required variable \"$this\" is set to \"$value\" but is not a type of ${R::class}" }
                value
            }
        } as R
    }

    fun build(): List<StepX> {
        return steps
    }
}

object iteration