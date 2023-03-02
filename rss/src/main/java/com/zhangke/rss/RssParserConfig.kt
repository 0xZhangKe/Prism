package com.zhangke.rss

import com.google.gson.Gson

object RssParserConfig {

    var gson: Gson by LazyBackingFieldDelegate(::Gson)

}
