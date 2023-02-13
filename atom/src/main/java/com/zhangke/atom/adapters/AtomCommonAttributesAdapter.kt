package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes

internal class AtomCommonAttributesAdapter : TypeAdapter<AtomCommonAttributes>() {

    companion object {

        const val COMMON_KEY = "commonAttributes"
    }

    override fun write(writer: JsonWriter, value: AtomCommonAttributes) {
        writer.beginObject()
        value.base?.let { writer.name("base").value(it) }
        value.lang?.let { writer.name("lang").value(it) }
        writer.endObject()
    }

    override fun read(input: JsonReader): AtomCommonAttributes {
        input.beginObject()
        var base: String? = null
        var lang: String? = null
        while (input.hasNext()) {
            when (input.nextName()) {
                "base" -> {
                    base = input.nextString()
                }

                "lang" -> {
                    lang = input.nextString()
                }

                else -> input.skipValue()
            }
        }
        input.endObject()
        return AtomCommonAttributes(base, lang)
    }
}