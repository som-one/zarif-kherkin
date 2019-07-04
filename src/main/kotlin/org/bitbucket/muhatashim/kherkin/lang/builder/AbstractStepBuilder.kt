package org.bitbucket.muhatashim.kherkin.lang.builder

import org.bitbucket.muhatashim.kherkin.lang.construct.StepX
import org.bitbucket.muhatashim.kherkin.lang.meta.*

@KherkinDsl
abstract class AbstractStepBuilder {
    protected val steps = mutableListOf<StepX>()

    fun iterate(data: List<Map<String, *>>, setup: IterationBuilder.() -> Unit) {
        require(data.isNotEmpty()) { "There must be at least one item in the provided data argument." }

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

    @KherkinDsl
    val Given = StepType("Given")
    @KherkinDsl
    val When = StepType("When")
    @KherkinDsl
    val Then = StepType("Then")
    @KherkinDsl
    val And = StepType("And")
    @KherkinDsl
    val But = StepType("But")

    inner class StepType(val name: String) {
        infix fun the(step: StepX) {
            val callSite = Thread.currentThread().stackTrace[2]
            steps += step.copy(
                meta = step.meta.copy(
                    keyword = this.name,
                    line = callSite.lineNumber,
                    result = ResultMeta(status = StatusMeta.skipped)
                )
            )
        }
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
                location = callSite.fileName + '.' + callSite.methodName
            ),
            arguments = mutableListOf(argumentFromPairs(args))
        )
    )
}
