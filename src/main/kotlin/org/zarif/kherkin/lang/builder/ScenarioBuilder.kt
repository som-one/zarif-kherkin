package org.zarif.kherkin.lang.builder

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.zarif.kherkin.lang.KherkinDsl
import org.zarif.kherkin.lang.construct.ScenarioX
import org.zarif.kherkin.lang.construct.StepX
import java.nio.charset.Charset


@KherkinDsl
class ScenarioBuilder {
    private val steps = mutableListOf<StepX>()
    var name: String? = null
    var description: String? = null
    var data: List<Map<String, *>> = listOf()

    fun csv(location: String): List<Map<String, String>> {
        val fileData: MutableList<Map<String, String>> = mutableListOf()
        val parser = CSVParser.parse(
            javaClass.getResource(location),
            Charset.defaultCharset(),
            CSVFormat.DEFAULT.withTrim().withHeader()
        )
        val records: List<CSVRecord> = parser.records
        val headerMap: Map<String, Int> = parser.headerMap
        fileData += records.map { it.toMap() }
        return fileData
    }

    inline fun steps(setup: StepBuilder.() -> Unit) {
        addSteps(StepBuilder(data).apply(setup).build())
    }

    fun addSteps(step: List<StepX>) {
        steps += step
    }

    fun build(): ScenarioX {
        requireNotNull(name) { "Scenario name cannot be null!" }
        return ScenarioX(name!!, description, steps)
    }
}