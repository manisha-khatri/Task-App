package com.example.taskapp.presentation.screen.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TaskScreenTopBar(totalTasks: Int, completedTasks: Int, onDeleteAllTasks: () -> Unit) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "TaskFlow Icon",
                    tint = Color(0xFF3B82F6)
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("TaskFlow", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("Simple task management", style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        actions = {
            // Delete All button
            TextButton(
                onClick = onDeleteAllTasks,
                colors = ButtonDefaults.textButtonColors(contentColor = Color.Red),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete All")
                Spacer(Modifier.width(4.dp))
                Text("Clear All")
            }
            Row(
                modifier = Modifier.padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(totalTasks.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("TOTAL", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(completedTasks.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF3B82F6))
                    Text("COMPLETED", style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        modifier = Modifier.background(Color.White)
    )
}