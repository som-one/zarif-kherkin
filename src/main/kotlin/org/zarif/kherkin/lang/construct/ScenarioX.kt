package org.zarif.kherkin.lang.construct

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class ScenarioX(
    val name: String,
    val description: String?,
    val steps: MutableList<StepX>
) {

    operator fun invoke() {
        logger.debug { "-- Scenario: $name: $description" }
        steps.forEach {
            it()
        }
    }
}
