package com.altiran.taskmaster.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(val id: Int, val description: String, var isDone: Boolean = false, var category: String? = null)
