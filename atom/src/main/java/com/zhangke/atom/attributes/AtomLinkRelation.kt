package com.zhangke.atom.attributes

@JvmInline
value class AtomLinkRelation(val type: String) {

    companion object {

        /**
         * The value "alternate" signifies that the IRI in the value of the href attribute identifies
         * an alternate version of the resource described by the containing element.
         */
        const val TYPE_ALTERNATE = "alternate"

        /**
         * The value "related" signifies that the IRI in the value of the href attribute identifies
         * a resource related to the resource described by the containing element.
         */
        const val TYPE_RELATED = "related"

        /**
         * The value "self" signifies that the IRI in the value of the href attribute identifies a resource equivalent to the containing element.
         */
        const val TYPE_SELF = "self"

        /**
         * The value "enclosure" signifies that the IRI in the value of the href attribute identifies a related resource that is potentially large in size and might require special handling.
         * For atom:link elements with rel="enclosure", the length attribute SHOULD be provided.
         */
        const val TYPE_ENCLOSURE = "enclosure"

        /**
         * The value "via" signifies that the IRI in the value of the href attribute
         * identifies a resource that is the source of the information provided in the containing element.
         */
        const val TYPE_VIA = "via"
    }
}