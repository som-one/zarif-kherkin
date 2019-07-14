package one.som.kherkin.lang.construct

import kotlinx.coroutines.CoroutineScope
import mu.KotlinLogging
import one.som.kherkin.lang.builder.cast
import one.som.kherkin.lang.builder.createEmbedding
import one.som.kherkin.lang.meta.ScenarioMeta

private val logger = KotlinLogging.logger {}

data class ScenarioX(
    val name: String,
    val description: String?,
    val steps: MutableList<StepX>,
    var extra: Map<String, *> = mapOf<String, String>(),
    var meta: ScenarioMeta
) {
    lateinit var feature: FeatureX
    lateinit var scenarioContext: MutableMap<Int, Any>

    suspend operator fun invoke(hooks: Hooks, callingFeature: FeatureX, coroutineScope: CoroutineScope? = null) {
        logger.debug { "-- Scenario: $name: $description" }
        val thisScenario = this@ScenarioX.copy().apply {
            feature = callingFeature
            scenarioContext = mutableMapOf()
        }
        hooks.beforeScenarios.forEach { it.invoke(thisScenario) }
        for (it in steps) {
            if (!it(hooks, thisScenario, coroutineScope)) {
                break
            }
        }
        hooks.afterScenarios.forEach { it.invoke(thisScenario) }
    }

    inline fun <reified K> fromContext(key: Key<K>): K? {
        val realKey = System.identityHashCode(key)
        return scenarioContext[realKey]?.let {
            require(it is K) {
                "Context variable found, but the found value does not match the required type " +
                        "\"${K::class.java.name}\""
            }
            return it
        }
    }

    fun <T> putContext(key: Key<T>, value: T) {
        val realKey = System.identityHashCode(key)
        scenarioContext[realKey] = value as Any
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

class Key<T>
