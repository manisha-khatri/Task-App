package com.example.taskapp.data.local

import com.example.taskapp.domain.model.Task

fun TaskEntity.toDomain(): Task = Task(
    id = id.toString(),
    title = title,
    date = date,
    status = status,
    priority = priority,
    isCompleted = isCompleted
)

fun Task.toEntity(): TaskEntity = TaskEntity(
    id = id.toInt(),
    title = title,
    date = date,
    status = status,
    priority = priority,
    isCompleted = isCompleted
)
