package com.zhangke.atom.elements

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomCategoryListAdapter
import com.zhangke.atom.adapters.AtomLinkListAdapter
import com.zhangke.atom.adapters.AtomPersonListAdapter
import com.zhangke.atom.attributes.*
import com.zhangke.atom.attributes.AtomLogo
import com.zhangke.atom.constructs.AtomDate
import com.zhangke.atom.constructs.AtomPerson
import com.zhangke.atom.constructs.AtomText
import com.zhangke.atom.metadata.AtomCategory
import com.zhangke.atom.metadata.AtomGenerator

/**
 * The "atom:feed" element is the document (i.e., top-level) element of
 * an Atom Feed Document, acting as a container for metadata and data
 * associated with the feed.  Its element children consist of metadata
 * elements followed by zero or more atom:entry child elements.
 *
 * If multiple atom:entry elements with the same atom:id value appear in
 * an Atom Feed Document, they represent the same entry.  Their
 * atom:updated timestamps SHOULD be different.  If an Atom Feed
 * Document contains multiple entries with the same atom:id, Atom
 * Processors MAY choose to display all of them or some subset of them.
 * One typical behavior would be to display only the entry with the
 * latest atom:updated timestamp.
 */
data class AtomFeed(
    override val base: String?,
    override val lang: String?,
    /**
     * atom:feed elements MUST contain one or more atom:author elements,
     * unless all of the atom:feed element's child atom:entry elements
     * contain at least one atom:author element.
     */
    @JsonAdapter(AtomPersonListAdapter::class)
    val author: List<AtomPerson>?,
    @JsonAdapter(AtomCategoryListAdapter::class)
    val category: List<AtomCategory>?,
    @JsonAdapter(AtomPersonListAdapter::class)
    val contributor: List<AtomPerson>?,
    val generator: AtomGenerator?,
    val icon: AtomIcon?,
    val logo: AtomLogo?,
    val id: AtomId,
    @JsonAdapter(AtomLinkListAdapter::class)
    val link: List<AtomLink>?,
    val rights: AtomText?,
    val subtitle: AtomText?,
    val title: AtomText,
    val updated: AtomDate,
    val entry: List<AtomEntry>?
) : AtomElement, AtomCommonAttributes