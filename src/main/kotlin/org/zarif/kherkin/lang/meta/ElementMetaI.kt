package org.zarif.kherkin.lang.meta

interface ElementMetaI {
    var description: String?
    var name: String?
    var keyword: String?
    var type: String?
    var line: Int?
    var steps: MutableList<StepMeta>?
}