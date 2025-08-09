package com.example.taskapp.data.remote

import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: ApiService) {

    fun getTasksFromServer(): Flow<List<Task>> = flow {
        val response = api.getTasks()
        if (response.isSuccessful) {
            emit(response.body()?.map { it.toDomain() } ?: emptyList())
        } else {
            throw Exception("HTTP ${response.code()} - ${response.message()}")
        }
    }

    suspend fun saveTaskToServer(task: Task) {
        val response = api.saveTask(task.toDto())
        if (!response.isSuccessful) {
            throw Exception("HTTP ${response.code()} - ${response.message()}")
        }
    }
}

