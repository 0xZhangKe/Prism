package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.attributes.AtomIcon
import com.zhangke.atom.attributes.AtomId

class AtomIconAdapter : TypeAdapter<AtomIcon>() {

    override fun write(out: JsonWriter, value: AtomIcon) {
        with(out) {
            if (value.hasCommonAttributes()) {
                beginObject()
                value.writeCommonAttributes()
                value.uri.writeSelfWith("uri")
                endObject()
            } else {
                out.value(value.uri)
            }
        }
    }

    override fun read(input: JsonReader): AtomIcon {
        var commonAttributes: AtomCommonAttributes? = null
        var uri = ""
        if (input.peek() == JsonToken.BEGIN_OBJECT) {
            commonAttributes = input.readCommon { name, reader ->
                when (name) {
                    "uri" -> uri = reader.nextString()
                    else -> reader.skipValue()
                }
            }
        } else {
            uri = input.nextString()
        }
        return AtomIcon(
            base = commonAttributes?.base,
            lang = commonAttributes?.lang,
            uri = uri
        )
    }
}