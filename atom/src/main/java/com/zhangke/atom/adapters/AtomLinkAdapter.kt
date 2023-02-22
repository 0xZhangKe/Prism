package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomCommonAttributes
import com.zhangke.atom.attributes.AtomLink

internal class AtomLinkAdapter : TypeAdapter<AtomLink>() {

    override fun write(out: JsonWriter, value: AtomLink) {
        out.beginObject()
        with(out) {
            value.writeCommonAttributes()
            value.href.writeSelfWith("href")
            value.hreflang?.writeSelfWith("hreflang")
            value.rel?.writeSelfWith("rel")
            value.type?.writeSelfWith("type")
            value.length?.writeSelfWith("length")
            value.title?.writeSelfWith("title")
        }
        out.endObject()
    }

    override fun read(input: JsonReader): AtomLink {
        var commonAttributes: AtomCommonAttributes? = null
        var href: String? = null
        var hrefLang: String? = null
        var rel: String? = null
        var type: String? = null
        var length: String? = null
        var title: String? = null
        commonAttributes = input.readCommon { name, _ ->
            when (name) {
                "href" -> href = input.nextString()
                "hreflang" -> hrefLang = input.nextString()
                "rel" -> rel = input.nextString()
                "type" -> type = input.nextString()
                "length" -> length = input.nextString()
                "title" -> title = input.nextString()
                else -> input.skipValue()
            }
        }
        return AtomLink(
            base = commonAttributes?.base,
            lang = commonAttributes?.lang,
            href = href!!,
            hreflang = hrefLang,
            type = type,
            length = length,
            title = title,
            rel = rel,
        )
    }
}