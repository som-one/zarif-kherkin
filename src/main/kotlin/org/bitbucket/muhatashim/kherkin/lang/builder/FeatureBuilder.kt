package org.bitbucket.muhatashim.kherkin.lang.builder

import kotlinx.coroutines.runBlocking
import org.bitbucket.muhatashim.kherkin.lang.construct.*
import org.bitbucket.muhatashim.kherkin.lang.meta.FeatureMeta
import org.bitbucket.muhatashim.kherkin.lang.meta.TagMeta

@KherkinDsl
class FeatureBuilder(val callSite: StackTraceElement) {
    private val scenarios = mutableListOf<ScenarioX>()
    var background: BackgroundX? = null
    var description: String? = null
    var name: String? = null
    var extra: Map<String, *> = mapOf<String, String>()
    var tags: Set<String> = setOf()

    inline fun Background(setup: BackgroundBuilder.() -> Unit) {
        background = BackgroundBuilder().apply(setup).build()
    }

    inline fun Example(name: String, examples: List<Map<String, *>>, setup: ExampleScenarioBuilder.() -> Unit) {
        require(!examples.isEmpty()) { "There must be at least one example in the provided examples argument." }

        examples.forEach {
            val scenarioCallSite = Thread.currentThread().stackTrace[2]
            addScenario(ExampleScenarioBuilder(it, scenarioCallSite).also { it.name = name }.apply(setup).build())
        }
    }

    inline fun Scenario(name: String, setup: SimpleScenarioBuilder.() -> Unit) {
        val scenarioCallSite = Thread.currentThread().stackTrace[2]
        addScenario(SimpleScenarioBuilder(scenarioCallSite).also { it.name = name }.apply(setup).build())
    }

    inline fun fScenario(name: String, hooks: Hooks = Hooks(), setup: SimpleScenarioBuilder.() -> Unit) {
        val allHooks = GlobalHooks + hooks
        val scenarioCallSite = Thread.currentThread().stackTrace[2]
        SimpleScenarioBuilder(scenarioCallSite).also { it.name = name }.apply(setup).build().also { scenario ->
            addScenario(scenario)
            runBlocking {
                requireNotNull(background) {
                    "Background must be defined before scenario in order to run a specific scenario"
                }()
                scenario(allHooks, build())
            }
        }
    }

    fun addScenario(vararg scenario: ScenarioX) {
        scenarios += scenario
    }

    fun build(): FeatureX {
        return FeatureX(
            name!!, description, background, scenarios,
            FeatureMeta(
                keyword = "Feature",
                id = callSite.fileName + "#$name",
                name = name!!,
                description = description ?: "",
                line = callSite.lineNumber,
                tags = tags.map { TagMeta(it) }.toMutableList(),
                uri = callSite.fileName,
                elements = ((background?.meta?.let { listOf(it) } ?: listOf())
                        + scenarios.map { it.meta }).toMutableList()
            )
        )
    }
}

fun Feature(name: String, setup: FeatureBuilder.() -> Unit): FeatureX {
    val featureCallSite = Thread.currentThread().stackTrace[2]
    FeatureBuilder(featureCallSite).also {
        it.name = name
        it.setup()
        return it.build()
    }
}
