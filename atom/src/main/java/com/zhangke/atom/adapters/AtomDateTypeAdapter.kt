package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.constructs.AtomDate
import java.util.*

internal class AtomDateTypeAdapter : TypeAdapter<AtomDate>() {

    private val commonAttributesAdapter = AtomCommonAttributesAdapter()

    override fun write(out: JsonWriter, value: AtomDate) {
        out.beginObject()
        value.commonAttrs?.let {
            out.name(AtomCommonAttributesAdapter.COMMON_KEY)
            commonAttributesAdapter.write(out, it)
        }
        value.dateTime?.let {
            out.name("dateTime").value(it.time)
        }
        out.endObject()
    }

    override fun read(input: JsonReader): AtomDate {
        var commonAttributes: AtomCommonAttributes? = null
        var dateTime: Date? = null
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
        return AtomDate(commonAttributes, dateTime)
    }
}