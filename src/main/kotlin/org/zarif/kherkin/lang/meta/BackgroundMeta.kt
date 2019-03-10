package org.zarif.kherkin.lang.meta

data class BackgroundMeta(
    override var description: String? = null,
    override var name: String? = null,
    override var keyword: String? = null,
    override var type: String? = null,
    override var line: Int? = null,
    override var steps: MutableList<StepMeta>? = null
) : ElementMetaI