package org.zarif.kherkin.lang

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.zarif.kherkin.lang.construct.FeatureX
import org.zarif.kherkin.lang.meta.FeatureMeta
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

class KherkinRunner(vararg val features: FeatureX) {

    fun invokeAndLog() {
        invokeAndLog(File("C:\\Users\\Muhatashim\\IdeaProjects\\untitled7\\" +"kherkin-results.json"))
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
        val metas = mutableListOf<FeatureMeta>()
        features.forEach {
            it()
            metas.add(it.meta)
        }
        return metas
    }
}