package com.example.taskapp.data.repository

import com.example.taskapp.data.local.LocalDataSource
import com.example.taskapp.data.remote.RemoteDataSource
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    val local: LocalDataSource,
    val remote: RemoteDataSource
): TaskRepository {

    // add task in both local + remote
    override suspend fun addTask(task: Task) {
        withContext(Dispatchers.IO) {
            local.saveTaskOffline(task)
            /*try {
                 remote.saveTaskToServer(task)
             } catch (e: Exception) {
                println("Error adding tasks in the server: ${e.message}")
             }*/
        }
    }

    /**
     * If DB is empty → fetch from API → save into DB.
     * DB updates → triggers new emission in the same flow → UI now gets serverTasks.
     */
    override fun getTasks(): Flow<List<Task>> {
        return local.getCachedTasks()
            .onEach { cachedTasks ->
                if (cachedTasks.isEmpty()) {
                    try {
                        val serverTasks = remote.getTasksFromServer()
                        serverTasks.collect { tasks ->
                            local.saveAllTasksOffline(tasks)
                        }
                    } catch (e: Exception) {
                        println("Error fetching tasks from server: ${e.message}")
                    }
                }
            }
            .flowOn(Dispatchers.IO)
    }
}