package com.example.taskapp.data.remote

import com.example.taskapp.util.Priority
import com.example.taskapp.util.TaskStatus

data class TaskDto(
    val id: Int,
    val title: String,
    val date: String,
    val status: TaskStatus,
    val priority: Priority,
    val isCompleted: Boolean = false
)
