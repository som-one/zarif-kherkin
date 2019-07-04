package one.som.kherkin.lang.meta

data class StateMeta(
    var output: MutableList<String>? = null,
    var result: ResultMeta? = null,
    var match: MatchMeta? = null
)
