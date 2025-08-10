package com.example.taskapp.data.local

import com.example.taskapp.domain.model.Task

fun TaskEntity.toDomain(): Task = Task(
    id = id,
    title = title,
    date = date,
    status = status,
    priority = priority,
    isCompleted = isCompleted
)

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        date = date,
        status = status,
        priority = priority,
        isCompleted = isCompleted
    )
}

