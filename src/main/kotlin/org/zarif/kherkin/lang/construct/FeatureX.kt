package org.zarif.kherkin.lang.construct

import mu.KotlinLogging


/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 2/27/2019
 * Time: 8:57 PM
 */
private val logger = KotlinLogging.logger {}

data class FeatureX(
    val name: String,
    val description: String?,
    val background: BackgroundX?,
    val scenarios: MutableList<ScenarioX>
) {

    operator fun invoke() {
        logger.debug { "- Feature: $name: $description" }
        scenarios.forEach {
            background?.invoke()
            it()
        }
    }
}