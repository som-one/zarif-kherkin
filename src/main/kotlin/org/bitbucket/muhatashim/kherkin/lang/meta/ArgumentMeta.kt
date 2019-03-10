package org.bitbucket.muhatashim.kherkin.lang.meta

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/6/2019
 * Time: 11:21 PM
 */
data class ArgumentMeta(
    var `var`: String? = null,
    var rows: MutableList<RowMeta>? = null,
    var offset: Int? = null
)

fun argumentFromPairs(pairs: Array<out Pair<String, String>>): ArgumentMeta {
    var rowMetas: MutableList<RowMeta> = mutableListOf()
    pairs.forEach { (key, value) ->
        rowMetas.add(RowMeta(mutableListOf(key, value)))
    }
    return ArgumentMeta(rows = rowMetas)
}