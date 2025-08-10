package com.example.taskapp.presentation.screen.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskapp.domain.model.TaskStatus

// Composable for the status indicator
@Composable
fun StatusIndicator(status: TaskStatus) {
    val color = when (status) {
        TaskStatus.IN_PROGRESS -> Color(0xFFF59E0B)
        TaskStatus.COMPLETED -> Color(0xFF10B981)
        TaskStatus.TODO -> Color(0xFF6B7280)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, RoundedCornerShape(50))
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = status.name.lowercase().replace("_", " ").replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF6B7280)
        )
    }
}