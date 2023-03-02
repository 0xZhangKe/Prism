package com.zhangke.atom

import com.google.gson.Gson

object AtomParserConfig {

    var gson: Gson by LazyBackingFieldDelegate(::Gson)

}
