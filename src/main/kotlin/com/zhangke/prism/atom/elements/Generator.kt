package com.zhangke.prism.atom.elements

import java.net.URI

/**
 * The "atom:generator" element's content identifies the agent used to generate a feed, for debugging and other purposes.
 */
data class Generator(
    /**
     * The atom:generator element MAY have a "uri" attribute whose value MUST be an IRI reference RFC3987.
     * When dereferenced, the resulting URI (mapped from an IRI, if necessary) SHOULD produce a representation that is relevant to that agent.
     */
    val uri: URI,
    /**
     * The atom:generator element MAY have a "version" attribute that indicates the version of the generating agent.
     */
    val version: Int,
)