package org.bitbucket.muhatashim.kherkin.lang.meta

/**
 * Created with IntelliJ IDEA.
 * User: Muhatashim
 * Date: 3/6/2019
 * Time: 11:20 PM
 */
data class MatchMeta(
    var arguments: MutableList<ArgumentMeta>? = null,
    var location: String? = null
)