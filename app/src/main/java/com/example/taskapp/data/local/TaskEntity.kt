package com.example.taskapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskapp.util.Priority
import com.example.taskapp.util.TaskStatus

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val date: String,
    val status: TaskStatus,
    val priority: Priority,
    val isCompleted: Boolean = false
)