package com.zhangke.atom

import java.io.File

class DocRepoHelper {

    fun getAtomRepoDir(): File{
        return File(getDocRepoDir(), "Atom/")
    }

    private fun getDocRepoDir(): File{
        return File("../DocRepo/")
    }
}