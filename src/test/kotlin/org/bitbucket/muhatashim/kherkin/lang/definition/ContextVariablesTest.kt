package org.bitbucket.muhatashim.kherkin.lang.definition

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.bitbucket.muhatashim.kherkin.lang.builder.step
import org.bitbucket.muhatashim.kherkin.lang.construct.Key

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/26/2019
 * Time: 12:45 AM
 */

val cool1 = Key<String>()
val cool2 = Key<String>()

fun `duck has done something cool`() = step {
    putContext(cool1, "Cool1")
}

fun `duck has done something cool 2`() = step {
    putContext(cool2, "Cool2")
}

fun `duck verifies that it has done something cool`() = step {
    getContext(cool1) shouldBe "Cool1"
}

fun `duck verifies that it has done something cool 2`() = step {
    getContext(cool2) shouldBe "Cool2"
}

fun `duck verifies that it has not done something cool`() = step {
    getContext(cool1) shouldNotBe "Cool1"
}

fun `duck verifies that it has not done something cool 2`() = step {
    getContext(cool2) shouldNotBe "Cool2"
}