package com.example.taskapp.data.local

import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val taskDao: TaskDao) {

    fun getCachedTasks(): Flow<List<Task>> =
        taskDao.getTasks()
            .map { entities ->
                entities.map { it.toDomain() }
        }

    suspend fun saveTaskOffline(task: Task) =
        taskDao.insertTask(task.toEntity())

    suspend fun saveAllTasksOffline(tasks: List<Task>) =
        taskDao.saveAllTasks(tasks.map { it.toEntity() })

    suspend fun deleteAllTasks() = taskDao.deleteAllTasks()
}