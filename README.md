
### Task App

## Simple task app, where user add tasks and have a checkbox for all the tasks.tasks are shown in the form of cards.

Operations: 
1. AddTask
2. GetTasks


✅ In domain:
- Task.kt – a data class representing your task (id, title, isDone, etc.)

- TaskRepository.kt – an interface defining the operations (addTask, getTasks, updateTask, etc.)

- UseCases (if you want to be more modular):
AddTaskUseCase.kt
GetTasksUseCase.kt
UpdateTaskUseCase.kt
DeleteTaskUseCase.kt


### TODO:
1. Add a dropdown for priority
