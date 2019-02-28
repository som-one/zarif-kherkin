package org.zarif.kherkin.lang.construct

class StepX(val execution: () -> Unit) {

    operator fun invoke() {
        execution.invoke()
    }
}