package org.bitbucket.muhatashim.kherkin.lang

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import kotlinx.coroutines.runBlocking
import org.bitbucket.muhatashim.kherkin.lang.construct.FeatureX
import org.bitbucket.muhatashim.kherkin.lang.construct.Hooks
import org.bitbucket.muhatashim.kherkin.lang.execution.builder.Flow
import org.bitbucket.muhatashim.kherkin.lang.meta.FeatureMeta
import java.io.File
import java.io.Writer

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/9/2019
 * Time: 7:49 PM
 */
fun main() {
    TODO()
}

class KherkinRunner(val flow: Flow) {

    constructor(
        vararg features: FeatureX,
        runParallel: Boolean = false,
        hooks: Hooks = Hooks()
    ) : this(Flow(hooks).apply {
        if (runParallel) parallel { features.forEach { -it } }
        else serial { features.forEach { -it } }
    })

    fun invokeAndLog() {
        invokeAndLog(File(System.getProperty("user.dir") + File.separator + "kherkin-results.json"))
    }

    fun invokeAndLog(file: File) {
        file.writer().use { invokeAndLog(it) }
    }

    fun invokeAndLog(outputWriter: Writer) {
        ObjectMapper().registerModule(KotlinModule())
            .setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY)
            .writeValue(outputWriter, invoke())
    }

    operator fun invoke(): List<FeatureMeta> {
        return runBlocking {
            flow.runSubFlows()
        }
    }
}