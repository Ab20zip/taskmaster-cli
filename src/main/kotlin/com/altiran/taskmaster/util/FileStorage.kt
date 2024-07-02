package com.altiran.taskmaster.util

import java.io.File

class FileStorage(private val filePath: String) {
    fun save(data: String) {
        File(filePath).writeText(data)
    }

    fun load(): String {
        val file = File(filePath)
        return if (file.exists()) {
            file.readText()
        } else {
            ""
        }
    }
}