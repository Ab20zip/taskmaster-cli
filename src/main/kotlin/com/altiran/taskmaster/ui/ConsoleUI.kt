package com.altiran.taskmaster.ui

import com.altiran.taskmaster.service.TaskService
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.int
import org.fusesource.jansi.Ansi.ansi
import org.fusesource.jansi.AnsiConsole

class AddTask(private val taskService: TaskService) : CliktCommand(help = "Add a new task") {
    private val description: String by option(help = "Task description").prompt("Description")
    private val category: String? by option(help = "Task category")

    override fun run() {
        taskService.addTask(description, category)
        echo(ansi().fgGreen().a("Task added: $description").reset())
    }
}

class RemoveTask(private val taskService: TaskService) : CliktCommand(help = "Remove a task") {
    private val id: Int by option(help = "Task ID").int().prompt("Task ID")

    override fun run() {
        taskService.removeTask(id)
        echo(ansi().fgGreen().a("Task removed: $id").reset())
    }
}

class DoneTask(private val taskService: TaskService) : CliktCommand(help = "Mark a task as done") {
    private val id: Int by option(help = "Task ID").int().prompt("Task ID")

    override fun run() {
        taskService.markTaskAsDone(id)
        echo(ansi().fgGreen().a("Task marked as done: $id").reset())
    }
}

class ListTasks(private val taskService: TaskService) : CliktCommand(help = "List tasks") {
    private val page: Int by option(help = "Page number").int().default(1)
    private val pageSize: Int by option(help = "Tasks per page").int().default(5)

    override fun run() {
        val tasks = taskService.listTasks(page, pageSize)
        if (tasks.isEmpty()) {
            echo(ansi().fgRed().a("No tasks found.").reset())
        } else {
            tasks.forEach { task ->
                echo(ansi().a("${task.id}. ${if (task.isDone) "[x]" else "[ ]"} ${task.description} (Category: ${task.category ?: "None"})"))
            }
        }
    }
}

class SearchTasks(private val taskService: TaskService) : CliktCommand(help = "Search tasks") {
    private val query: String by option(help = "Search query").prompt("Query")

    override fun run() {
        val tasks = taskService.searchTasks(query)
        if (tasks.isEmpty()) {
            echo(ansi().fgRed().a("No tasks found for query: $query").reset())
        } else {
            tasks.forEach { task ->
                echo(ansi().a("${task.id}. ${if (task.isDone) "[x]" else "[ ]"} ${task.description} (Category: ${task.category ?: "None"})"))
            }
        }
    }
}

class TodoApp(private val taskService: TaskService) : CliktCommand() {
    override fun run() = Unit
}

fun runApp(taskService: TaskService) {
    AnsiConsole.systemInstall()
    TodoApp(taskService).subcommands(
        AddTask(taskService),
        RemoveTask(taskService),
        DoneTask(taskService),
        ListTasks(taskService),
        SearchTasks(taskService)
    ).main(emptyArray())
    AnsiConsole.systemUninstall()
}