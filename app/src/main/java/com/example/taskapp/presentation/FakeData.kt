package com.example.taskapp.presentation

import com.example.taskapp.util.Priority
import com.example.taskapp.util.TaskStatus
import com.example.taskapp.domain.model.Task

fun fakeData(): List<Task> {
    return  listOf(
        Task("1", "Design new landing page", "Today", TaskStatus.IN_PROGRESS, Priority.HIGH, false),
        Task("2", "Review project proposal", "Yesterday", TaskStatus.COMPLETED, Priority.LOW, true),
        Task("3", "Update documentation", "2 days ago", TaskStatus.TODO, Priority.MEDIUM, false),
        Task("4", "Setup development environment", "3 days ago", TaskStatus.COMPLETED, Priority.HIGH, true),
        Task("5", "Prepare client presentation", "1 week ago", TaskStatus.TODO, Priority.MEDIUM, false),
        Task("6", "Fix responsive layout issues", "5 days ago", TaskStatus.COMPLETED, Priority.HIGH, true),
    )
}