package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.metadata.AtomGenerator

class AtomGeneratorAdapter : TypeAdapter<AtomGenerator>() {

    override fun write(out: JsonWriter, value: AtomGenerator) {
        with(out) {
            if (!value.hasCommonAttributes() &&
                value.uri.isNullOrEmpty() &&
                value.version.isNullOrEmpty()
            ) {
                out.value(value.name)
            } else {
                beginObject()
                value.writeCommonAttributes()
                value.name.writeSelfWith("name")
                value.version?.writeSelfWith("version")
                value.uri?.writeSelfWith("uri")
                endObject()
            }
        }
    }

    override fun read(input: JsonReader): AtomGenerator {
        var commonAttributes: AtomCommonAttributes? = null
        var generatorName = ""
        var version: String? = null
        var uri: String? = null
        if (input.peek() == JsonToken.BEGIN_OBJECT) {
            commonAttributes = input.readCommon { name, reader ->
                when (name) {
                    "name" -> generatorName = input.nextString()
                    "uri" -> uri = input.nextString()
                    "version" -> version = input.nextString()
                    else -> input.skipValue()
                }
            }
        } else {
            generatorName = input.nextString()
        }
        return AtomGenerator(
            base = commonAttributes?.base,
            lang = commonAttributes?.lang,
            name = generatorName,
            version = version,
            uri = uri
        )
    }
}