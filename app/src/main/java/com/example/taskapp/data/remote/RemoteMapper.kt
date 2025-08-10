package com.example.taskapp.data.remote

import com.example.taskapp.domain.model.Task

fun TaskDto.toDomain(): Task = Task(
    id = id,
    title = title,
    date = date,
    status = status,
    priority = priority,
    isCompleted = isCompleted
)

fun Task.toDto(): TaskDto = TaskDto(
    id = id,
    title = title,
    date = date,
    status = status,
    priority = priority,
    isCompleted = isCompleted
)