package com.zhangke.atom.adapters

import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes

context(JsonWriter)
internal fun String.writeSelfWith(name: String) {
    name(name).value(this@writeSelfWith)
}

context(JsonWriter)
internal fun AtomCommonAttributes.writeCommonAttributes() {
    lang?.writeSelfWith("lang")
    base?.writeSelfWith("base")
}

//internal fun JsonReader.readCommonAttributes(): AtomCommonAttributes? {
//    val lang =
//    return commonAttributesAdapter.read(this)
//}

internal fun JsonReader.readCommon(block: (name: String, reade: JsonReader) -> Unit): AtomCommonAttributes? {
    var base: String? = null
    var lang: String? = null
    readALL { name, reader ->
        when (name) {
            "base" -> base = reader.nextString()
            "lang" -> lang = reader.nextString()
            else -> block(name, reader)
        }
    }
    if (base.isNullOrEmpty() && lang.isNullOrEmpty()) return null
    return PlaceholderAttribute(base = base, lang = lang)
}

data class PlaceholderAttribute(
    override val base: String?,
    override val lang: String?,
) : AtomCommonAttributes

internal fun JsonReader.readALL(reader: (name: String, reader: JsonReader) -> Unit) {
    beginObject()
    while (hasNext()) {
        reader(nextName(), this)
    }
    endObject()
}

internal fun AtomCommonAttributes.hasCommonAttributes(): Boolean {
    return !base.isNullOrEmpty() && !lang.isNullOrEmpty()
}