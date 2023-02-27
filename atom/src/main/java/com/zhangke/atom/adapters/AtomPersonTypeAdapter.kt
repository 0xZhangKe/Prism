package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.constructs.AtomPerson

class AtomPersonTypeAdapter : TypeAdapter<AtomPerson>() {

    override fun write(out: JsonWriter, value: AtomPerson) {
        with(out) {
            if (!value.hasCommonAttributes() &&
                value.email.isNullOrEmpty() &&
                value.uri.isNullOrEmpty()
            ) {
                // String type
                out.value(value.name)
            } else {
                beginObject()
                value.writeCommonAttributes()
                value.name.writeSelfWith("name")
                value.uri?.writeSelfWith("uri")
                value.email?.writeSelfWith("email")
                endObject()
            }
        }
    }

    override fun read(input: JsonReader): AtomPerson {
        var commonAttributes: AtomCommonAttributes? = null
        var authorName = ""
        var email: String? = null
        var uri: String? = null
        if (input.peek() == JsonToken.BEGIN_OBJECT) {
            commonAttributes = input.readCommon { name, reader ->
                when (name) {
                    "name" -> {
                        // Workaround, Some shit Source have more than one name.
                        if (input.peek() == JsonToken.BEGIN_ARRAY) {
                            val nameList = mutableListOf<String>()
                            input.beginArray()
                            while (input.hasNext()) {
                                nameList += input.nextString()
                            }
                            input.endArray()
                            authorName = nameList.joinToString(",")
                        } else {
                            authorName = input.nextString()
                        }
                    }

                    "email" -> email = input.nextString()
                    "uri" -> uri = input.nextString()
                    else -> input.skipValue()
                }
            }
        } else {
            authorName = input.nextString()
        }
        return AtomPerson(
            base = commonAttributes?.base,
            lang = commonAttributes?.lang,
            name = authorName,
            uri = uri,
            email = email,
        )
    }
}