package org.bitbucket.muhatashim.kherkin.lang.meta

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/6/2019
 * Time: 11:15 PM
 */
data class StepMeta(
    var keyword: String? = null,
    var name: String? = null,
    var result: ResultMeta? = null,
    var line: Int? = null,
    var match: MatchMeta? = null,
    var arguments: MutableList<ArgumentMeta>? = null,
    var outputs: MutableList<Any>? = null,
    var embeddings: MutableList<EmbeddingMeta>? = null,
    var before: MutableList<ResultMeta>? = null,
    var after: MutableList<ResultMeta>? = null
)
