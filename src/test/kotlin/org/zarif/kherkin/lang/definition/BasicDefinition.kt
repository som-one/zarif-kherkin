package org.zarif.kherkin.lang.definition

import org.zarif.kherkin.lang.builder.step
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 2/27/2019
 * Time: 11:12 PM
 */


val logger = Logger.getGlobal()

fun log(s: String) = logger.log(Level.INFO, s)

fun `duck is on sign in page`() = step {
    log(">>> Duck navigated to the sign in page")
}

fun `duck enters the following credentials`(username: String, password: String) =
    step("username" to username, "password" to password) {
        log(">>> Duck entered $username, $password")
    }

fun `duck is logged in`() = step {
    log(">>> Duck is logged in")
}

fun `duck sees the homepage`() = step {
    log(">>> Duck sees the homepage")
}