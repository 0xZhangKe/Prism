package com.zhangke.atom

import org.json.XML
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import java.lang.reflect.Constructor
import javax.xml.parsers.DocumentBuilderFactory

class XmlParser {

//    fun <T> parse(xml: String, clazz: Class<T>): T {
//        val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(InputSource(StringReader(xml)))
//        repeat(document.childNodes.length) {
//            println("${document.childNodes.item(it).nodeName}:${document.childNodes.item(it).nodeValue}")
//        }

//        clazz.constructors.forEach {
//            it.parameters.forEach {
//                it.name
//            }
//        }
//    }

    private fun <T> xmlToObject(node: Node, clazz: Class<T>): T {
        val childNodes = node.childNodes
        val constructor = pickConstructor(clazz)
        val params = arrayOfNulls<Any>(constructor.parameterCount)
        for (param in constructor.parameters) {
            val name = param.name
            val type = param.type
        }
        val obj = constructor.newInstance(params)

        return obj
    }

    private fun findNamedValue(node: Node, name: String, type: Class<*>): Any? {
        val childNodes = node.childNodes
        for (i in 0 until childNodes.length) {
            val item = childNodes.item(i)
            if (item.nodeName == name) {
                return transformType(item.nodeValue, type)
            }
        }
        return null
    }

    private fun transformType(value: String, type: Class<*>): Any? {

        return when (type) {
            Boolean::class.java -> value.toBoolean()
            Char::class.java -> value.toCharArray()[0]
            Short::class.java -> value.toShort()
            Int::class.java -> value.toInt()
            Long::class.java -> value.toLong()
            Float::class.java -> value.toFloat()
            Double::class.java -> value.toDouble()
            String::class.java -> value
            Array::class.java -> {

            }

            else -> null
        }
    }

    private fun <T> pickConstructor(clazz: Class<T>): Constructor<T> {
        val constructors = clazz.constructors
        constructors.sortBy { it.parameterCount }
        @Suppress("UNCHECKED_CAST")
        return constructors[0] as Constructor<T>
    }
}

fun main() {
//    val xml = File("atom/TestFile.xml").readText()
//    val orgJson = XML.toJSONObject(xml)
//    println()
//    val document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(InputSource(StringReader(xml)))
//    val feedDoc = document.childNodes.item(0)
//    repeat(feedDoc.childNodes.length) {
//        val item = feedDoc.childNodes.item(it)
//        println("${item.nodeType}:${item.nodeName}:${item.attributes}:${item.nodeValue}")
//    }
}