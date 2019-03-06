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

    inline fun Scenario(setup: ScenarioBuilder.() -> Unit) {
        addScenario(ScenarioBuilder().apply(setup).build())
    }

    inline fun fScenario(setup: ScenarioBuilder.() -> Unit) {
        ScenarioBuilder().apply(setup).build().also { x ->
            addScenario(x)
            requireNotNull(background) {
                "Background must be defined before scenario in order to run a specific scenario"
            }()
            x()
        }
    }

    fun addScenario(scenario: ScenarioX) {
        scenarios.add(scenario)
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