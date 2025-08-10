package com.example.taskapp.presentation.screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskapp.domain.model.Task

// Composable for the list of tasks
@Composable
fun TaskList(tasks: List<Task>, onTaskCompletionToggled: (Task) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(tasks) { task ->
                TaskCard(
                    task = task,
                    onTaskCompletionToggled = { onTaskCompletionToggled(task) }
                )
            }
        }
    }
}
