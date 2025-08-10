package com.example.taskapp.domain.usecase

import com.example.taskapp.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteAllTasksUseCase @Inject constructor (private val repository: TaskRepository) {
    suspend operator fun invoke() = repository.deleteAllTasks()
}