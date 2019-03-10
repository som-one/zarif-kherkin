package org.zarif.kherkin.lang.construct

import mu.KotlinLogging
import org.zarif.kherkin.lang.builder.cast
import org.zarif.kherkin.lang.builder.createEmbedding
import org.zarif.kherkin.lang.meta.ScenarioMeta

private val logger = KotlinLogging.logger {}

data class ScenarioX(
    val name: String,
    val description: String?,
    val steps: MutableList<StepX>,
    var extra: Map<String, *> = mapOf<String, String>(),
    var meta: ScenarioMeta
) {

    operator fun invoke(hooks: Hooks) {
        logger.debug { "-- Scenario: $name: $description" }

        hooks.beforeScenarios.forEach { it.invoke(this@ScenarioX) }
        for (it in steps) {
            if (!it(hooks)) {
                break
            }
        }
        hooks.afterScenarios.forEach { it.invoke(this@ScenarioX) }
    }

    fun embed(bytes: ByteArray, mimeType: String) {
        if (meta.embeddings == null) meta.embeddings = mutableListOf()
        meta.embeddings!!.add(createEmbedding(bytes, mimeType))
    }

    inline fun <reified T> getExtra(key: String): T {
        val extra = requireNotNull(extra[key]) { "$key does not exist in extras for ${meta.id}" }
        return cast(extra) { "$key is not a type of ${T::class}" }
    }
}
