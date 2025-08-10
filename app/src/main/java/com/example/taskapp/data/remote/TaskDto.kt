package com.example.taskapp.data.remote

import com.example.taskapp.domain.model.Priority
import com.example.taskapp.domain.model.TaskStatus

data class TaskDto(
    val id: Long,
    val title: String,
    val date: String,
    val status: TaskStatus,
    val priority: Priority,
    val isCompleted: Boolean = false
)
