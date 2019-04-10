package org.bitbucket.muhatashim.kherkin.lang.builder

import org.bitbucket.muhatashim.kherkin.lang.construct.StepX
import org.bitbucket.muhatashim.kherkin.lang.meta.*

@KherkinDsl
abstract class AbstractStepBuilder {
    protected val steps = mutableListOf<StepX>()

    infix fun StepType.the(step: StepX) {
        val callSite = Thread.currentThread().stackTrace[2]
        steps += step.copy(
            meta = step.meta.copy(
                keyword = this.name,
                lineNumber = callSite.lineNumber,
                result = ResultMeta(status = StatusMeta.skipped)
            )
        )
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

fun step(execution: suspend StepX.() -> Unit): StepX {
    return step(arrayOf(), Thread.currentThread().stackTrace[2], execution)
}

fun step(vararg args: Pair<String, String>, execution: suspend StepX.() -> Unit): StepX {
    return step(args, Thread.currentThread().stackTrace[2], execution)
}

private fun step(
    args: Array<out Pair<String, String>>,
    callSite: StackTraceElement,
    execution: suspend StepX.() -> Unit
): StepX {
    //Since finding the call site name depends on the stack trace, we cannot use default parameters
    return StepX(
        execution,
        meta = StepMeta(
            name = callSite.methodName,
            keyword = "",
            outputs = mutableListOf(),
            match = MatchMeta(
                arguments = mutableListOf(argumentFromPairs(args)),
                location = callSite.fileName + '.' + callSite.methodName
            )
        )
    )
}

open class StepType(val name: String)
object Given : StepType("Given")
object When : StepType("When")
object Then : StepType("Then")
object And : StepType("And")
object But : StepType("But")
