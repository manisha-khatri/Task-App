package com.example.taskapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskapp.domain.model.Task
import com.example.taskapp.presentation.TaskUiState
import com.example.taskapp.presentation.screen.sections.AddTaskSection
import com.example.taskapp.presentation.screen.sections.TaskFilterTabs
import com.example.taskapp.presentation.screen.sections.TaskList
import com.example.taskapp.presentation.screen.sections.TaskScreenTopBar
import com.example.taskapp.domain.model.Priority

@Composable
fun TaskScreen(
    uiState: TaskUiState,
    onNewTaskTitleChange: (String) -> Unit,
    onAddTask: () -> Unit,
    onFilterSelected: (String) -> Unit,
    onTaskCompletionToggled: (Task) -> Unit,
    onPrioritySelected: (Priority) -> Unit,
    onDeleteAllTasks: () -> Unit
) {
    Scaffold(
        topBar = {
            TaskScreenTopBar(
                totalTasks = uiState.tasks.size,
                completedTasks = uiState.tasks.count { it.isCompleted },
                onDeleteAllTasks = onDeleteAllTasks
            )
        },
        containerColor = Color(0xFFF3F4F6)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // "Add New Task" section
            AddTaskSection(
                newTaskTitle = uiState.newTaskTitle,
                onNewTaskTitleChange = onNewTaskTitleChange,
                selectedPriority = uiState.selectedPriority,
                onPrioritySelected = onPrioritySelected,
                onAddTask = onAddTask
            )
            Spacer(Modifier.height(16.dp))

            // Task filter tabs
            TaskFilterTabs(
                selectedFilter = uiState.selectedFilter,
                onFilterSelected = onFilterSelected
            )
            Spacer(Modifier.height(16.dp))

            // List of tasks
            val filteredTasks = when (uiState.selectedFilter) {
                "Pending" -> uiState.tasks.filter { !it.isCompleted }
                "Completed" -> uiState.tasks.filter { it.isCompleted }
                else -> uiState.tasks
            }
            TaskList(
                tasks = filteredTasks,
                onTaskCompletionToggled = onTaskCompletionToggled
            )
        }
    }
}
