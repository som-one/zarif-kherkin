package one.som.kherkin.lang.meta

data class FeatureMeta(
    var id: String? = null,
    var description: String? = null,
    var tags: MutableList<TagMeta>? = null,
    var name: String? = null,
    var keyword: String? = null,
    var line: Int? = null,
    var uri: String? = null,
    var elements: MutableList<ElementMetaI>? = null
)
