package org.bitbucket.muhatashim.kherkin.lang.execution.builder

import org.bitbucket.muhatashim.kherkin.lang.construct.Hooks
import org.bitbucket.muhatashim.kherkin.lang.meta.FeatureMeta

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/29/2019
 * Time: 10:55 PM
 */

@ExecutionDsl
open class Flow(val hooks: Hooks = Hooks()) {

    val processes = mutableListOf<Process>()

    fun parallel(setup: Parallel.() -> Unit) {
        processes += Parallel(hooks).apply(setup)
    }

    fun serial(setup: Serial.() -> Unit) {
        processes += Serial(hooks).apply(setup)
    }

    suspend fun runSubFlows(): MutableList<FeatureMeta> {
        return mutableListOf<FeatureMeta>().apply {
            processes.forEach {
                it(this)
                it.runSubFlows()
            }
        }
    }
}

fun flow(hooks: Hooks = Hooks(), setup: Flow.() -> Unit): Flow {
    return Flow().apply(setup)
}
