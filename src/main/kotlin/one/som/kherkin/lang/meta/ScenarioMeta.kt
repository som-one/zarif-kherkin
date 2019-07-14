package one.som.kherkin.lang.meta

data class ScenarioMeta(
    var id: String? = null,
    var tags: MutableSet<TagMeta>? = null,
    override var description: String? = null,
    override var name: String? = null,
    override var keyword: String? = null,
    override var type: String? = null,
    override var line: Int? = null,
    override var steps: MutableList<StepMeta>? = null,
    var before: StateMeta? = null,
    var after: StateMeta? = null,
    var embeddings: MutableList<EmbeddingMeta>? = null
) : ElementMetaI
