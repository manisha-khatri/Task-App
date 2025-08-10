package com.example.taskapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskapp.domain.model.Priority
import com.example.taskapp.domain.model.TaskStatus

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val date: String,
    val status: TaskStatus,
    val priority: Priority,
    val isCompleted: Boolean = false
)