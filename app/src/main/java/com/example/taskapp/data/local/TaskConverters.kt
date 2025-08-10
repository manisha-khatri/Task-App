package com.example.taskapp.data.local


import androidx.room.TypeConverter
import com.example.taskapp.domain.model.Priority
import com.example.taskapp.domain.model.TaskStatus

class TaskConverters {

    @TypeConverter
    fun fromTaskStatus(status: TaskStatus): String {
        return status.name
    }

    @TypeConverter
    fun toTaskStatus(value: String): TaskStatus {
        return TaskStatus.valueOf(value)
    }

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(value: String): Priority {
        return Priority.valueOf(value)
    }
}
