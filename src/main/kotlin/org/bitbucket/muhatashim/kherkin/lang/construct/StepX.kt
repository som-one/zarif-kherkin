package org.bitbucket.muhatashim.kherkin.lang.construct

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import org.bitbucket.muhatashim.kherkin.lang.builder.createEmbedding
import org.bitbucket.muhatashim.kherkin.lang.meta.ResultMeta
import org.bitbucket.muhatashim.kherkin.lang.meta.StatusMeta
import org.bitbucket.muhatashim.kherkin.lang.meta.StepMeta
import java.io.PrintWriter
import java.io.StringWriter
import java.util.logging.Handler
import java.util.logging.LogRecord
import java.util.logging.Logger

data class StepX(
    val execution: suspend StepX.() -> Unit,
    val datum: Map<String, *> = mapOf<String, Any>(),
    var meta: StepMeta = StepMeta(),
    val currentScope: CoroutineScope = GlobalScope
) {
    var scenario: ScenarioX? = null

    suspend operator fun invoke(
        hooks: Hooks,
        callingScenario: ScenarioX?,
        coroutineScope: CoroutineScope? = null
    ): Boolean {
        val logHandler = StringLogHandler()
        val thisStep = this.copy(currentScope = coroutineScope ?: GlobalScope).apply {
            callingScenario?.let { scenario = it }
        }

        hooks.beforeSteps.forEach { it.invoke(thisStep) }
        Logger.getGlobal().addHandler(logHandler)
        meta.result = runAndGetResult(thisStep, execution)
        Logger.getGlobal().removeHandler(logHandler)
        hooks.afterSteps.forEach { it.invoke(thisStep) }
        meta.outputs!!.addAll(logHandler.logs)
        return meta.result!!.status == StatusMeta.passed
    }

    inline fun <reified K> getContext(key: Key<K>): K? {
        return requireNotNull(scenario) { "Scenario must be defined for this step" }.fromContext(key)
    }

    fun <T> putContext(key: Key<T>, value: T) {
        requireNotNull(scenario) { "Scenario must be defined for this step" }.putContext(key, value)
    }

    fun embed(bytes: ByteArray, mimeType: String) {
        if (meta.embeddings == null) meta.embeddings = mutableListOf()
        meta.embeddings!!.add(createEmbedding(bytes, mimeType))
    }

    fun getCoroutine(): GlobalScope {
        return GlobalScope
    }
}

class StringLogHandler : Handler() {
    val logs: MutableList<String> = mutableListOf()
    override fun publish(record: LogRecord) {
        logs.add(
            "[${record.level}] ${record.sourceClassName}#${record.sourceMethodName}: ${record.message}"
        )
    }

    override fun flush() {}
    override fun close() {}
    override fun toString(): String {
        return logs.joinToString { "\n" }
    }
}


suspend fun <P> runAndGetResult(instance: P, execution: suspend P.() -> Unit): ResultMeta {
    val result = ResultMeta()
    val startTime = System.currentTimeMillis()
    try {

        instance.execution()
    } catch (t: Throwable) {
        val stringWriter = StringWriter()
        PrintWriter(stringWriter).use {
            t.printStackTrace(it)
            t.printStackTrace()
        }
        result.error_message = stringWriter.toString()
    } finally {
        result.duration = System.currentTimeMillis() - startTime
        result.status = result.error_message?.let { StatusMeta.failed } ?: StatusMeta.passed
        return result
    }
}
