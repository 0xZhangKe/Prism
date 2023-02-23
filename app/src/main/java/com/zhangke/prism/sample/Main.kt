package com.zhangke.prism.sample

import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

fun main() {
    val json = JsonObject().apply {
        addProperty("name", "Webb")
        addProperty("link", "google.com")
//        add("link", JsonArray().apply {
//            add("www.google.com")
//            add("www.facebook.com")
//            add("www.youtube.com")
//        })
    }
    val foo = gson.fromJson(json, Foo::class.java)
    println(foo)
}

class StringListAdapter : FooListAdapter(String::class.java)

@JsonAdapter(StringListAdapter::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE)
annotation class StringList

open class FooListAdapter(private val clazz: Class<*>) : TypeAdapter<List<*>>() {

    override fun write(out: JsonWriter, value: List<*>) {
        val delegate = gson.getAdapter(List::class.java)
        delegate.write(out, value)
        println(delegate)
    }

    override fun read(input: JsonReader): List<*> {
        return if (input.peek() == JsonToken.BEGIN_ARRAY) {
            val delegate = gson.getAdapter(List::class.java)
            println(delegate)
            delegate.read(input)
        } else {
            val element = gson.getAdapter(clazz).read(input)
            listOf(element)
        }
    }
}

data class Foo(
    val name: String,
    @JsonAdapter(StringListAdapter::class)
    val link: List<String>
)