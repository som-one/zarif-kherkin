package org.bitbucket.muhatashim.kherkin.lang.construct


object GlobalHooks : Hooks()
open class Hooks(
    val beforeSteps: MutableList<(StepX) -> Unit> = mutableListOf(),
    val afterSteps: MutableList<(StepX) -> Unit> = mutableListOf(),
    val beforeScenarios: MutableList<(ScenarioX) -> Unit> = mutableListOf(),
    val afterScenarios: MutableList<(ScenarioX) -> Unit> = mutableListOf()
) {

    operator fun plus(hook: Hooks): Hooks {
        return Hooks(
            beforeSteps = hook.beforeSteps,
            afterSteps = hook.afterSteps,
            beforeScenarios = hook.beforeScenarios,
            afterScenarios = hook.afterScenarios
        )
    }

    fun beforeSteps(execution: (StepX) -> Unit) {
        beforeSteps += execution
    }

    fun afterSteps(execution: (StepX) -> Unit) {
        afterSteps += execution
    }

    fun beforeScenarios(execution: (ScenarioX) -> Unit) {
        beforeScenarios += execution
    }

    fun afterScenarios(execution: (ScenarioX) -> Unit) {
        afterScenarios += execution
    }
}