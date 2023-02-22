package com.zhangke.atom.constructs

import com.google.gson.annotations.JsonAdapter
import com.zhangke.atom.adapters.AtomDateTypeAdapter
import com.zhangke.atom.attributes.AtomCommonAttributes
import java.util.*

@JsonAdapter(AtomDateTypeAdapter::class)
data class AtomDate(
    override val base: String?,
    override val lang: String?,
    /**
     * A Date construct is an element whose content MUST conform to the "date-time" production in [RFC3339](https://datatracker.ietf.org/doc/html/rfc3339).
     * In addition, an uppercase "T" character MUST be used to separate date and time,
     * and an uppercase "Z" character MUST be present in the absence of a numeric time zone offset.
     */
    val dateTime: Date?
): AtomCommonAttributes