package com.zhangke.atom.metadata

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomGeneratorAdapter
import com.zhangke.atom.attributes.AtomCommonAttributes

/**
 * The "atom:generator" element's content identifies the agent used to generate a feed, for debugging and other purposes.
 */
@JsonAdapter(AtomGeneratorAdapter::class)
data class AtomGenerator(
    override val base: String?,
    override val lang: String?,
    /**
     * The atom:generator element MAY have a "uri" attribute whose value MUST be an IRI reference RFC3987.
     * When dereferenced, the resulting URI (mapped from an IRI, if necessary) SHOULD produce a representation that is relevant to that agent.
     */
    val uri: String?,
    /**
     * The atom:generator element MAY have a "version" attribute that indicates the version of the generating agent.
     */
    val version: String?,

    val name: String
) : AtomCommonAttributes