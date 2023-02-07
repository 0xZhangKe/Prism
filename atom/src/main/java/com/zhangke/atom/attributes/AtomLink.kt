package com.zhangke.atom.attributes

/**
 * The "atom:link" element defines a reference from an entry or feed to a Web resource.
 * This specification assigns no meaning to the content (if any) of this element.
 */
data class AtomLink(
    /**
     * The "href" attribute contains the link's IRI.
     * atom:link elements MUST have an href attribute, whose value MUST be a IRI referenceRFC3987.
     */
    val href: String,
    val rel: AtomLinkRelation?,
    /**
     * On the link element, the "type" attribute's value is an advisory media type: it is a hint about the type of the representation that is
     * expected to be returned when the value of the href attribute is dereferenced.
     * Note that the type attribute does not override the actual media type returned with the representation.
     * Link elements MAY have a type attribute, whose value MUST conform to the syntax of a MIME media type MIMEREG.
     */
    val type: String?,
    /**
     * The "hreflang" attribute's content describes the language of the resource pointed to by the href attribute.
     * When used together with the rel="alternate", it implies a translated version of the entry.
     * Link elements MAY have an hreflang attribute, whose value MUST be a language tag RFC3066.
     */
    val hreflang: String?,
    /**
     * The "title" attribute conveys human-readable information about the link.
     * The content of the "title" attribute is Language-Sensitive.
     */
    val title: String?,
    /**
     * The "length" attribute indicates an advisory length of the linked content in octets;
     * it is a hint about the content length of the representation returned when the IRI in the href attribute is mapped to a URI and dereferenced.
     * Note that the length attribute does not override the actual content length of the representation as reported by the underlying protocol.
     */
    val length: String?,
)
