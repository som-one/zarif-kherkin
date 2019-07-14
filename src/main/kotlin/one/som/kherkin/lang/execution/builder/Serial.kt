package one.som.kherkin.lang.execution.builder

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import one.som.kherkin.lang.construct.Hooks
import one.som.kherkin.lang.meta.FeatureMeta

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
