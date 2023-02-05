package com.zhangke.atom.metadata

import com.zhangke.atom.attributes.CommonAttributes

/**
 * The "atom:generator" element's content identifies the agent used to generate a feed, for debugging and other purposes.
 */
data class AtomGenerator(
    val commonAttrs: CommonAttributes?,
    /**
     * The atom:generator element MAY have a "uri" attribute whose value MUST be an IRI reference RFC3987.
     * When dereferenced, the resulting URI (mapped from an IRI, if necessary) SHOULD produce a representation that is relevant to that agent.
     */
    val uri: String,
    /**
     * The atom:generator element MAY have a "version" attribute that indicates the version of the generating agent.
     */
    val version: Int,
)