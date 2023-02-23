package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.constructs.AtomText

class AtomTextAdapter : TypeAdapter<AtomText>() {

    override fun write(out: JsonWriter, value: AtomText) {
        with(out) {
            value.writeCommonAttributes()
            value.type.writeSelfWith("type")
            value.text?.writeSelfWith("text")
            value.xhtmlDiv?.writeSelfWith("xhtmlDiv")
        }
    }

    override fun read(input: JsonReader): AtomText {
        var type = AtomText.TYPE_TEXT
        var text = ""
        var xhtmlDiv: String? = null
        var commonAttributes: AtomCommonAttributes? = null
        if (input.peek() == JsonToken.BEGIN_OBJECT) {
            commonAttributes = input.readCommon { name, reader ->
                when (name) {
                    "type" -> type = reader.nextString()
                    "text" -> text = reader.nextString()
                    "xhtmlDiv" -> xhtmlDiv = reader.nextString()
                    else -> reader.skipValue()
                }
            }
        } else {
            text = input.nextString()
        }
        return AtomText(
            base = commonAttributes?.base,
            lang = commonAttributes?.lang,
            text = text,
            type = type,
            xhtmlDiv = xhtmlDiv
        )
    }
}