package one.som.kherkin.lang.execution.builder

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import one.som.kherkin.lang.construct.Hooks
import one.som.kherkin.lang.meta.FeatureMeta

class Parallel(hooks: Hooks) : Process(hooks) {
    override suspend fun invoke(metaList: MutableList<FeatureMeta>) {
        metaList.apply {
            val jobs = mutableListOf<Job>()
            featureXs.forEach {
                jobs.add(GlobalScope.launch {
                    it(hooks, this)
                    add(it.meta)
                })
            }
            jobs.joinAll()
        }
    }
}
