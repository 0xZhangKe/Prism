package com.zhangke.atom

import com.zhangke.atom.constructs.AtomText
import com.zhangke.atom.transform.AtomTextTransform
import org.simpleframework.xml.transform.Matcher
import org.simpleframework.xml.transform.Transform

internal class AtomParseMather : Matcher {

    override fun match(type: Class<*>?): Transform<*>? {
        return when (type) {
            AtomText::class.java -> AtomTextTransform()
            else -> null
        }
    }
}