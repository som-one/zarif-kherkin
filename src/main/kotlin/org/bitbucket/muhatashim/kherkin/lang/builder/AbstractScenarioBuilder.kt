package org.bitbucket.muhatashim.kherkin.lang.builder

import org.bitbucket.muhatashim.kherkin.lang.construct.ScenarioX
import org.bitbucket.muhatashim.kherkin.lang.construct.StepX
import org.bitbucket.muhatashim.kherkin.lang.meta.ScenarioMeta
import org.bitbucket.muhatashim.kherkin.lang.meta.TagMeta


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