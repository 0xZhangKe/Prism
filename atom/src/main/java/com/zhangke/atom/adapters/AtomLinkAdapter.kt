package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.attributes.AtomLink

internal class AtomLinkAdapter: TypeAdapter<AtomLink>() {

    override fun write(out: JsonWriter?, value: AtomLink?) {
        TODO("Not yet implemented")
    }

    override fun read(`in`: JsonReader?): AtomLink {
        TODO("Not yet implemented")
    }
}