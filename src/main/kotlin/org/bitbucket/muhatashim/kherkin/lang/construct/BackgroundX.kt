package org.bitbucket.muhatashim.kherkin.lang.construct

import mu.KotlinLogging
import org.bitbucket.muhatashim.kherkin.lang.meta.BackgroundMeta

private val logger = KotlinLogging.logger {}

data class BackgroundX(
    val name: String?,
    val description: String?,
    val steps: MutableList<StepX>,
    var meta: BackgroundMeta
) {

    suspend operator fun invoke(hooks: Hooks = Hooks()) {
        val allHooks = GlobalHooks + hooks

        logger.debug { "-- Background: ${name ?: "unnamed"}: ${description ?: "no description"}" }
        steps.forEach {
            it(allHooks, null)
        }
    }
}
