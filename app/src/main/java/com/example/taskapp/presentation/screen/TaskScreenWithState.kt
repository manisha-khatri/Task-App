package com.example.taskapp.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.taskapp.domain.model.Task
import com.example.taskapp.presentation.TaskUiState
import com.example.taskapp.presentation.fakeData
import com.example.taskapp.util.Priority
import com.example.taskapp.util.TaskStatus

/**
 * A stateful composable that holds and manages the state for the TaskScreen.
 * This is where state would typically be hoisted from a ViewModel.
 */
@Composable
fun TaskScreenWithState() {
    var tasks by remember { mutableStateOf(fakeData()) }    // State to hold the list of task
    var newTaskTitle by remember { mutableStateOf("") }     // State for the new task input field
    var selectedFilter by remember { mutableStateOf("All Tasks") }  // State for the selected filter tab

    TaskScreen(
        uiState = TaskUiState(tasks, newTaskTitle, selectedFilter),
        onNewTaskTitleChange = { newTaskTitle = it },
        onAddTask = {
            if (newTaskTitle.isNotBlank()) {
                tasks = tasks + Task(
                    title = newTaskTitle,
                    date = "Just now", // TODO: fix date
                    status = TaskStatus.IN_PROGRESS,
                    priority = Priority.MEDIUM,
                    isCompleted = false
                )
                newTaskTitle = "" // Clear the input field
            }
        },
        onFilterSelected = { selectedFilter = it },
        onTaskCompletionToggled = { task ->
            tasks = tasks.map {
                if (it.id == task.id) {
                    it.copy(
                        isCompleted = !it.isCompleted,
                        status = if (!it.isCompleted) TaskStatus.COMPLETED else TaskStatus.IN_PROGRESS
                    )
                } else {
                    it
                }
            }
        }
    )
}