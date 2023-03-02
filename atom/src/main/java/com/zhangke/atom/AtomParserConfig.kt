package com.zhangke.atom

import com.google.gson.Gson

object AtomConfig {

    var gson: Gson by LazyBackingFieldDelegate(::Gson)

}
