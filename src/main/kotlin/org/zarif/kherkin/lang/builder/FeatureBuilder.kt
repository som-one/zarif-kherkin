package org.zarif.kherkin.lang.builder

import org.zarif.kherkin.lang.construct.BackgroundX
import org.zarif.kherkin.lang.construct.FeatureX
import org.zarif.kherkin.lang.construct.ScenarioX

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 2/27/2019
 * Time: 8:42 PM
 */

class FeatureBuilder {
    private val scenarios = mutableListOf<ScenarioX>()
    var background: BackgroundX? = null
    var description: String? = null
    var name: String? = null

    inline fun Background(setup: BackgroundBuilder.() -> Unit) {
        BackgroundBuilder().also {
            it.setup()
            background = it.build()
        }
    }

    inline fun Scenario(setup: ScenarioBuilder.() -> Unit) {
        ScenarioBuilder().also {
            it.setup()
            addScenario(it.build())
        }
    }

    inline fun fScenario(setup: ScenarioBuilder.() -> Unit) {
        ScenarioBuilder().also {
            it.setup()
            it.build().also { x ->
                addScenario(x)
                requireNotNull(background) { "Background must be defined before scenario in order to run a specific scenario" }
                background!!()
                x()
            }
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