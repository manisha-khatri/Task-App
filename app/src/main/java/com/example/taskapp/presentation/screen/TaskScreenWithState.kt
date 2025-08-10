package com.example.taskapp.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taskapp.presentation.TaskViewModel

/**
 * A stateful composable that holds and manages the state for the TaskScreen.
 * This is where state would typically be hoisted from a ViewModel.
 */
  @Composable
  fun TaskScreenWithState(
       viewModel: TaskViewModel = hiltViewModel()
   ) {
       // Collect the UI state from the ViewModel's StateFlow.
       // This makes the composable recompose whenever the state changes.
       val uiState by viewModel.uiState.collectAsStateWithLifecycle()

       // The composable is now stateless. It only receives the state and event handlers.
    TaskScreen(
        uiState = uiState,
        onNewTaskTitleChange = viewModel::onNewTaskTitleChange,
        onAddTask = viewModel::onAddTask,
        onFilterSelected = viewModel::onFilterSelected,
        onTaskCompletionToggled = viewModel::onTaskCompletionToggled,
        onPrioritySelected = viewModel::onPrioritySelected
    )
   }
