package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.constructs.AtomDate
import java.util.*

internal class AtomDateTypeAdapter : TypeAdapter<AtomDate>() {

    private val commonAttributesAdapter = AtomCommonAttributesAdapter()

    override fun write(out: JsonWriter, value: AtomDate) {
        if (value.commonAttrs != null) {
            // OBJECT model
            out.beginObject()
            out.name(AtomCommonAttributesAdapter.COMMON_KEY)
            commonAttributesAdapter.write(out, value.commonAttrs)
            if (value.dateTime != null) {
                out.name("dateTime").value(RFC3339Format.fromDate(value.dateTime))
            }
            out.endObject()
        } else if (value.dateTime != null) {
            out.name("dateTime").value(RFC3339Format.fromDate(value.dateTime))
        }
    }

    override fun read(input: JsonReader): AtomDate {
        var commonAttributes: AtomCommonAttributes? = null
        var dateTime: Date? = null
        if (input.peek() == JsonToken.BEGIN_OBJECT) {
            input.beginObject()
            while (input.hasNext()) {
                when (input.nextName()) {
                    AtomCommonAttributesAdapter.COMMON_KEY -> {
                        commonAttributes = commonAttributesAdapter.read(input)
                    }

                    "dateTime" -> {
                        dateTime = Date(input.nextLong())
                    }

                    else -> input.skipValue()
                }
            }
            input.endObject()
        } else {
            dateTime = RFC3339Format.toDate(input.nextString())
        }
        return AtomDate(commonAttributes, dateTime)
    }
}