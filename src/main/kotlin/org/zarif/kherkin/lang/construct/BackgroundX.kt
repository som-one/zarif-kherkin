package org.zarif.kherkin.lang.construct

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

data class BackgroundX(
    val name: String?,
    val description: String?,
    val steps: MutableList<StepX>
) {

    operator fun invoke() {
        logger.debug { "-- Background: ${name ?: "unnamed"}: ${description ?: "no description"}" }
        steps.forEach {
            it()
        }
    }
}