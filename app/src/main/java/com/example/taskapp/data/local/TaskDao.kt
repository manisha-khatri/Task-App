package com.example.taskapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun saveTask(task: TaskEntity)

    @Insert
    suspend fun saveAllTasks(tasks: List<TaskEntity>)
}