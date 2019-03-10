package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.ScenarioX
import org.zarif.kherkin.lang.construct.StepX
import org.zarif.kherkin.lang.meta.ScenarioMeta
import org.zarif.kherkin.lang.meta.TagMeta


@KherkinDsl
abstract class AbstractScenarioBuilder(val callSite: StackTraceElement? = null) {
    var name: String? = null
    var description: String? = null
    var extra: MutableMap<String, *> = mutableMapOf<String, String>()
    var tags: MutableSet<String> = mutableSetOf()
    private val steps = mutableListOf<StepX>()

    fun addSteps(step: List<StepX>) {
        steps += step
    }

    fun build(): ScenarioX {
        requireNotNull(name) { "Scenario must be named!" }
        return ScenarioX(
            name!!, description, steps, extra,
            ScenarioMeta(
                id = callSite!!.fileName + "#$name",
                keyword = "Scenario",
                type = "scenario",
                name = name!!,
                description = description ?: "",
                line = callSite.lineNumber,
                tags = tags.map { TagMeta(it) }.toMutableSet(),
                steps = steps.map { it.meta }.toMutableList()
            )
        )
    }
}