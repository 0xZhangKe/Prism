package com.zhangke.atom.transform

import com.zhangke.atom.constructs.AtomText
import org.simpleframework.xml.transform.Transform

class AtomTextTransform : Transform<AtomText> {

    override fun read(value: String?): AtomText {
        println("Read: $value")
        return AtomText(null, AtomText.TYPE_TEXT, "", "")
    }

    override fun write(value: AtomText?): String {
        println("Write: $value")
        return ""
    }
}