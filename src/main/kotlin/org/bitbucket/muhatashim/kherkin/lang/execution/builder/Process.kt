package org.bitbucket.muhatashim.kherkin.lang.execution.builder

import org.bitbucket.muhatashim.kherkin.lang.construct.FeatureX
import org.bitbucket.muhatashim.kherkin.lang.construct.Hooks
import org.bitbucket.muhatashim.kherkin.lang.meta.FeatureMeta

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
