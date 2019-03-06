package org.zarif.kherkin.lang.construct

data class StepX(val execution: () -> Unit, var datum: Map<String, *> = mapOf<String, Any>()) {

    operator fun invoke() {
        execution.invoke()
    }
}