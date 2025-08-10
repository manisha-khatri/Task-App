package com.example.taskapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.usecase.AddTaskUseCase
import com.example.taskapp.domain.usecase.DeleteAllTasksUseCase
import com.example.taskapp.domain.usecase.GetTasksUseCase
import com.example.taskapp.domain.model.Priority
import com.example.taskapp.domain.model.TaskStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTask: AddTaskUseCase,
    private val getTasks: GetTasksUseCase,
    private val deleteAllTasks: DeleteAllTasksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getTasks().collectLatest { tasks ->
                _uiState.update { it.copy(tasks = tasks) }
            }
        }
    }

    /**
     * Handles the user changing the text in the "add new task" input field.
     */
    fun onNewTaskTitleChange(newTitle: String) {
        _uiState.update { it.copy(newTaskTitle = newTitle) }
    }

    /**
     * Handles the user clicking the "Add Task" button.
     * Now calls the AddTaskUseCase to save the new task.
     */
    fun onAddTask() {
        if (_uiState.value.newTaskTitle.isNotBlank()) {
            viewModelScope.launch {

                val calendar = Calendar.getInstance()
                val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(calendar.time)

                val newTask = Task(
                    id = 0,
                    title = _uiState.value.newTaskTitle,
                    date = formattedDate, //TODO: fix date
                    status = TaskStatus.IN_PROGRESS,
                    priority = _uiState.value.selectedPriority,
                    isCompleted = false
                )
                addTask(newTask)
                _uiState.update { it.copy(newTaskTitle = "") }
            }
        }
    }

    /**
     * Handles the user selecting a new filter tab.
     */
    fun onFilterSelected(filter: String) {
        _uiState.update { it.copy(selectedFilter = filter) }
    }

    /**
     * Handles the user toggling the completion status of a task.
     * NOTE: A real implementation would also call a 'UpdateTaskUseCase' here.
     */
    fun onTaskCompletionToggled(task: Task) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    tasks = it.tasks.map { currentTask ->
                        if (currentTask.id == task.id) {
                            currentTask.copy(
                                isCompleted = !currentTask.isCompleted,
                                status = if (!currentTask.isCompleted) TaskStatus.COMPLETED else TaskStatus.IN_PROGRESS
                            )
                        } else {
                            currentTask
                        }
                    }
                )
            }
        }
    }

    fun onPrioritySelected(priority: Priority) {
        _uiState.update { it.copy(selectedPriority = priority) }
    }

    fun onDeleteAllTasks() {
        viewModelScope.launch {
            deleteAllTasks()
            // The UI will automatically update because the `getTasks` flow will emit
            // a new, empty list after the deletion.
        }
    }
}
