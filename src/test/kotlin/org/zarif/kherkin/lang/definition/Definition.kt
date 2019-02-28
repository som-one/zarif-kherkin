package org.zarif.kherkin.lang.definition

import org.zarif.kherkin.lang.builder.step

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 2/27/2019
 * Time: 11:12 PM
 */
val `duck is on sign in page` = step {
    println("---Start---")
    println(">>> Duck navigated to the sign in page")
}

fun `duck enters the following credentials`(username: String, password: String) = step {
    println(">>> Duck entered $username, $password")
}

val `duck is logged in` = step {
    println(">>> Duck is logged in")
}

val `duck sees the homepage` = step {
    println(">>> Duck sees the homepage")
}