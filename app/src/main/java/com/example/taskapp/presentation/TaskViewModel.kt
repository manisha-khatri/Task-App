package com.example.taskapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.usecase.AddTaskUseCase
import com.example.taskapp.domain.usecase.GetTasksUseCase
import com.example.taskapp.util.Priority
import com.example.taskapp.util.TaskStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val addTask: AddTaskUseCase,
    private val getTasks: GetTasksUseCase
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
                val newTask = Task(
                    id = UUID.randomUUID().toString(),
                    title = _uiState.value.newTaskTitle,
                    date = "Just now",
                    status = TaskStatus.IN_PROGRESS,
                    priority = Priority.MEDIUM,
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
    fun onTaskCompletionToggled(task: Task) { //TODO: API call
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
}


/*
    private val _taskUiState = MutableStateFlow<TaskUiState>(TaskUiState.Loading)
    val taskUiState: StateFlow<TaskUiState> = _taskUiState

    fun loadTasks() {
        viewModelScope.launch {
            getTask()
                .onStart { _taskUiState.value = TaskUiState.Loading }
                .catch { e -> _taskUiState.value = TaskUiState.Error(e.message.toString()) }
                .collect { tasks ->
                    if (tasks.isEmpty()) {
                        _taskUiState.value = TaskUiState.Error("No tasks found") // or create Empty state
                    } else {
                        _taskUiState.value = TaskUiState.Success(tasks)
                    }
                }
        }
    }

    fun addNewTask(task: Task) {
        viewModelScope.launch {
            try {
                addTask(task)
                // The UI will update automatically because loadTasks() is already collecting the Flow.
            } catch (e: Exception) {
                _taskUiState.value = TaskUiState.Error(e.message ?: "Failed to add task")
            }
        }
    }*/
