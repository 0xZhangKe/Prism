package com.zhangke.prism.atom.constructs

import com.zhangke.prism.atom.attributes.CommonAttributes
import java.util.*

data class AtomDate(
    val commonAttrs: CommonAttributes?,
    /**
     * A Date construct is an element whose content MUST conform to the "date-time" production in [RFC3339](https://datatracker.ietf.org/doc/html/rfc3339).
     * In addition, an uppercase "T" character MUST be used to separate date and time,
     * and an uppercase "Z" character MUST be present in the absence of a numeric time zone offset.
     */
    val dateTime: Date?
)