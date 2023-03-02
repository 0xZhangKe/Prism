package com.zhangke.rss

import java.io.File

class DocRepoHelper {

    fun getRssRepoDir(): File{
        return File(getDocRepoDir(), "Rss")
    }

    private fun getDocRepoDir(): File{
        return File("../DocRepo/")
    }
}