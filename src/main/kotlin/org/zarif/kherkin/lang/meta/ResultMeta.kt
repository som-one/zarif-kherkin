package org.zarif.kherkin.lang.meta

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/6/2019
 * Time: 11:23 PM
 */
data class ResultMeta(
    var duration: Long? = null,
    var status: StatusMeta? = null,
    var error_message: String? = null
)