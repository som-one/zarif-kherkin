package org.bitbucket.muhatashim.kherkin.lang.execution.builder

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bitbucket.muhatashim.kherkin.lang.construct.Hooks
import org.bitbucket.muhatashim.kherkin.lang.meta.FeatureMeta

class Serial(hooks: Hooks) : Process(hooks) {
    override suspend fun invoke(metaList: MutableList<FeatureMeta>) {
        metaList.apply {
            featureXs.forEach {
                GlobalScope.launch { it(hooks, this) }.join()
                add(it.meta)
            }
        }
    }
}
