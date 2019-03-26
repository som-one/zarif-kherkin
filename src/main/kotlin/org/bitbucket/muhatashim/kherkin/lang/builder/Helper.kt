package org.bitbucket.muhatashim.kherkin.lang.builder

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.bitbucket.muhatashim.kherkin.lang.meta.EmbeddingMeta
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/6/2019
 * Time: 9:04 PM
 */
private object Helper

fun csv(location: String): List<Map<String, String>> {
    val fileData: MutableList<Map<String, String>> = mutableListOf()
    val parser = CSVParser.parse(
        Helper.javaClass.getResource(location),
        Charset.defaultCharset(),
        CSVFormat.DEFAULT.withTrim().withHeader()
    )
    val records: List<CSVRecord> = parser.records
    fileData += records.map { it.toMap() }
    return fileData
}

inline fun <reified R> cast(value: Any, lazyErrorMessage: () -> String): R {
    return when (R::class) {
        String::class -> value.toString()
        Integer::class -> value.toString().toInt()
        Long::class -> value.toString().toLong()
        Double::class -> value.toString().toDouble()
        Float::class -> value.toString().toFloat()
        Date::class -> SimpleDateFormat().format(value)
        else -> {
            require(value is R, lazyErrorMessage)
            value
        }
    } as R
}

fun createEmbedding(bytes: ByteArray, mimeType: String): EmbeddingMeta {
    return EmbeddingMeta(
        data = Base64.getEncoder().encodeToString(bytes),
        mime_type = mimeType
    )
}