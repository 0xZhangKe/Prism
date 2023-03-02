package com.zhangke.atom

import java.io.File

class DocRepoHelper {

    fun getAtomRepoDir(): File{
        return File(getDocRepoDir(), "Atom/")
    }

    fun getRssRepoDir(): File{
        return File(getDocRepoDir(), "Rss")
    }

    fun getDocRepoDir(): File{
        return File("../DocRepo/")
    }
}