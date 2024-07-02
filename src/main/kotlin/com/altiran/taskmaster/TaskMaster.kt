package com.altiran.taskmaster

import com.altiran.taskmaster.service.TaskService
import com.altiran.taskmaster.ui.runApp
import com.altiran.taskmaster.util.FileStorage

class TaskMaster {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val storage = FileStorage("tasks.json")
            val taskService = TaskService(storage)
            runApp(taskService)
        }
    }
}