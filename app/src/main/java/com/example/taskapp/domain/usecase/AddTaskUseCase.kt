package com.example.taskapp.domain.usecase

import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor (private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task) = repository.addTask(task)
}