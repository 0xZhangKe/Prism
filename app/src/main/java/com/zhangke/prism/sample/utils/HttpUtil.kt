package com.zhangke.prism.sample.utils

import okhttp3.OkHttpClient
import okhttp3.Request

object HttpUtil {

    private val client = OkHttpClient.Builder().build()

    fun get(url: String): String? {
        return Request.Builder()
            .get()
            .url(url)
            .build()
            .let { client.newCall(it) }
            .execute()
            .body
            ?.string()
    }
}