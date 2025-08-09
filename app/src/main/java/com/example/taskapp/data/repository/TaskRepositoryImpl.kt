package com.example.taskapp.data.repository

import com.example.taskapp.data.local.LocalDataSource
import com.example.taskapp.data.remote.RemoteDataSource
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    val local: LocalDataSource,
    val remote: RemoteDataSource
): TaskRepository {

    // add task in both local + remote
    override suspend fun addTask(task: Task) {
        local.saveTaskOffline(task)
        remote.saveTaskToServer(task)
    }

    /**
     * If DB is empty → fetch from API → save into DB.
     * DB updates → triggers new emission in the same flow → UI now gets serverTasks.
     */
    override fun getTasks(): Flow<List<Task>> {
        return local.getCachedTasks()
            .flatMapLatest { cachedTasks ->
                if(cachedTasks.isNotEmpty()) {
                    flowOf(cachedTasks)
                } else {
                    remote.getTasksFromServer()
                        .onEach { serverTasks ->
                            local.saveAllTasksOffline(serverTasks)
                        }
                }
            }
            .flowOn(Dispatchers.IO)
    }
}