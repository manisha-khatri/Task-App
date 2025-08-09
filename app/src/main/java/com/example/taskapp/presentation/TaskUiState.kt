package com.example.taskapp.presentation

import com.example.taskapp.domain.model.Task

/**
 * Data class to hold all the UI state for the TaskScreen.
 * This is a single source of truth for the screen's state.
 */
data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val newTaskTitle: String = "",
    val selectedFilter: String = "All Tasks"
)