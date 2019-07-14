package one.som.kherkin.lang.execution.builder

import one.som.kherkin.lang.construct.FeatureX
import one.som.kherkin.lang.construct.Hooks
import one.som.kherkin.lang.meta.FeatureMeta

abstract class Process(hooks: Hooks) : Flow(hooks) {
    val featureXs = mutableListOf<FeatureX>()

    operator fun FeatureX.unaryPlus() {
        featureXs += this
    }

    operator fun FeatureX.unaryMinus() {
        featureXs += this
    }

    abstract suspend operator fun invoke(metaList: MutableList<FeatureMeta>)
}
