package com.example.taskapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskapp.util.Priority


// Composable for the priority tag
@Composable
fun PriorityTag(priority: Priority) {
    val backgroundColor = when (priority) {
        Priority.HIGH -> Color(0xFFFEE2E2)
        Priority.MEDIUM -> Color(0xFFF3E8FF)
        Priority.LOW -> Color(0xFFD1FAE5)
    }
    val textColor = when (priority) {
        Priority.HIGH -> Color(0xFFEF4444)
        Priority.MEDIUM -> Color(0xFF9333EA)
        Priority.LOW -> Color(0xFF10B981)
    }
    Text(
        text = "${priority.name.lowercase().replaceFirstChar { it.uppercase() }} Priority",
        style = MaterialTheme.typography.labelSmall,
        color = textColor,
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}