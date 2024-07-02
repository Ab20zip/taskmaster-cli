package com.altiran.taskmaster.service

import com.altiran.taskmaster.model.Task
import com.altiran.taskmaster.util.FileStorage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TaskService(private val storage: FileStorage) {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    init {
        loadTasks()
    }

    fun addTask(description: String, category: String? = null) {
        val task = Task(nextId++, description, category = category)
        tasks.add(task)
        saveTasks()
    }

    fun removeTask(id: Int) {
        tasks.removeIf { it.id == id }
        saveTasks()
    }

    fun markTaskAsDone(id: Int) {
        tasks.find { it.id == id }?.isDone = true
        saveTasks()
    }

    fun listTasks(page: Int, pageSize: Int): List<Task> {
        return tasks.drop((page - 1) * pageSize).take(pageSize)
    }

    fun searchTasks(query: String): List<Task> {
        return tasks.filter { it.description.contains(query, ignoreCase = true) }
    }

    private fun saveTasks() {
        val json = Json.encodeToString(tasks)
        storage.save(json)
    }

    private fun loadTasks() {
        val json = storage.load()
        if (json.isNotBlank()) {
            val loadedTasks: List<Task> = Json.decodeFromString(json)
            tasks.clear()
            tasks.addAll(loadedTasks)
            nextId = tasks.maxOfOrNull { it.id }?.plus(1) ?: 1
        }
    }
}