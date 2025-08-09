package com.example.taskapp.domain.model

import com.example.taskapp.util.Priority
import com.example.taskapp.util.TaskStatus
import java.util.UUID


data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val date: String,
    val status: TaskStatus,
    val priority: Priority,
    val isCompleted: Boolean = false
)
