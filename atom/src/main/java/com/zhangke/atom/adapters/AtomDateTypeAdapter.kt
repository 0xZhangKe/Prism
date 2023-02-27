package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.constructs.AtomDate
import java.util.*

internal class AtomDateTypeAdapter : TypeAdapter<AtomDate>() {

    override fun write(out: JsonWriter, value: AtomDate) {
        with(out) {
            if (value.hasCommonAttributes()) {
                // OBJECT mode
                beginObject()
                value.writeCommonAttributes()
                if (value.dateTime != null) {
                    AtomDateParser.fromDate(value.dateTime).writeSelfWith("dateTime")
                }
                endObject()
            } else if (value.dateTime != null) {
                out.value(RFC3339Format.fromDate(value.dateTime))
            } else {
                null
            }
        }
    }

    override fun read(input: JsonReader): AtomDate {
        var commonAttributes: AtomCommonAttributes? = null
        var dateTime: Date? = null
        if (input.peek() == JsonToken.BEGIN_OBJECT) {
            commonAttributes = input.readCommon { name, reader ->
                when (name) {
                    "dateTime" -> {
                        dateTime = Date(reader.nextLong())
                    }

                    else -> reader.skipValue()
                }
            }
        } else {
            dateTime = AtomDateParser.toDate(input.nextString())
        }
        return AtomDate(
            base = commonAttributes?.base,
            lang = commonAttributes?.lang,
            dateTime
        )
    }
}