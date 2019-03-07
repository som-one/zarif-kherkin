package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.BackgroundX
import org.zarif.kherkin.lang.construct.FeatureX
import org.zarif.kherkin.lang.construct.ScenarioX

@KherkinDsl
class FeatureBuilder {
    private val scenarios = mutableListOf<ScenarioX>()
    var background: BackgroundX? = null
    var description: String? = null
    var name: String? = null

    inline fun Background(setup: BackgroundBuilder.() -> Unit) {
        background = BackgroundBuilder().apply(setup).build()
    }

    inline fun Example(examples: List<Map<String, *>>, setup: ExampleScenarioBuilder.() -> Unit) {
        require(!examples.isEmpty()) { "There must be at least one example in the provided examples argument." }

        examples.forEach {
            addScenario(ExampleScenarioBuilder(it).apply(setup).build())
        }
    }

    inline fun Scenario(setup: SimpleScenarioBuilder.() -> Unit) {
        addScenario(SimpleScenarioBuilder().apply(setup).build())
    }

    inline fun fScenario(setup: SimpleScenarioBuilder.() -> Unit) {
        SimpleScenarioBuilder().apply(setup).build().also { x ->
            addScenario(x)
            requireNotNull(background) {
                "Background must be defined before scenario in order to run a specific scenario"
            }()
            x()
        }
    }

    fun addScenario(vararg scenario: ScenarioX) {
        scenarios += scenario
    }

    fun build(): FeatureX {
        return FeatureX(name!!, description, background, scenarios)
    }
}

fun Feature(setup: FeatureBuilder.() -> Unit): FeatureX {
    FeatureBuilder().also {
        it.setup()
        return it.build()
    }
}