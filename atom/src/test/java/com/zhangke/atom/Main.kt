package com.zhangke.atom

import com.google.gson.Gson
import com.google.gson.JsonObject


fun main(){
    val json = JsonObject().apply {
        addProperty("name", "ZhangKe")
    }
    println(Gson().fromJson(json, Foo::class.java).children.toString())
}

data class Foo(
    val name: String,
    val children: List<String>
)