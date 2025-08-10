package com.example.taskapp.domain.model

data class Task(
    val id: Long = 0,
    val title: String,
    val date: String,
    val status: TaskStatus,
    val priority: Priority,
    val isCompleted: Boolean = false
)
