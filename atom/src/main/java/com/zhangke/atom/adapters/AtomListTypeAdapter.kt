package com.zhangke.atom.adapters

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zhangke.atom.AtomConfig
import com.zhangke.atom.attributes.AtomLink
import com.zhangke.atom.constructs.AtomPerson
import com.zhangke.atom.metadata.AtomCategory

open class AtomListTypeAdapter(private val clazz: Class<*>) : TypeAdapter<List<*>>() {

    override fun write(out: JsonWriter, value: List<*>) {
        val delegate = AtomConfig.gson.getAdapter(List::class.java)
        delegate.write(out, value)
    }

    override fun read(input: JsonReader): List<*> {
        return if (input.peek() == JsonToken.BEGIN_ARRAY) {
            val delegate = AtomConfig.gson.getAdapter(List::class.java)
            delegate.read(input)
        } else {
            val element = AtomConfig.gson.getAdapter(clazz).read(input)
            listOf(element)
        }
    }
}

class AtomLinkListAdapter : AtomListTypeAdapter(AtomLink::class.java)

class AtomCategoryListAdapter: AtomListTypeAdapter(AtomCategory::class.java)

class AtomPersonListAdapter: AtomListTypeAdapter(AtomPerson::class.java)