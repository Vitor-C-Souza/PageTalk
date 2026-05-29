package com.pagetalk.app.ui.presentation.importpdf.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.presentation.importpdf.UploadStatus
import com.pagetalk.app.ui.theme.Success

@Composable
fun RecentUploadItem(
    name: String,
    details: String,
    status: UploadStatus,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (status is UploadStatus.InProgress) colorScheme.primary.copy(alpha = 0.2f)
            else Color.White.copy(alpha = 0.05f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Icon Box
                val iconBoxBrush = when (status) {
                    is UploadStatus.Complete -> Brush.linearGradient(
                        listOf(
                            Success,
                            Color(0xFF22C55E)
                        )
                    )

                    is UploadStatus.InProgress -> Brush.linearGradient(
                        listOf(
                            colorScheme.primary,
                            colorScheme.secondary
                        )
                    )

                    else -> Brush.linearGradient(
                        listOf(
                            colorScheme.surfaceVariant,
                            colorScheme.surfaceVariant
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(iconBoxBrush, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        tint = if (status is UploadStatus.Queued) colorScheme.outline else Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                    Text(
                        text = details,
                        color = colorScheme.outline,
                        fontSize = 12.sp
                    )
                }

                // Status Icon/Action
                when (status) {
                    is UploadStatus.Complete -> {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Success.copy(alpha = 0.2f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Success,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    is UploadStatus.InProgress -> {
                        val infiniteTransition = rememberInfiniteTransition(label = "loading")
                        val rotation by infiniteTransition.animateFloat(
                            initialValue = 0f,
                            targetValue = 360f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(1000, easing = LinearEasing)
                            ),
                            label = "rotation"
                        )
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(colorScheme.primary.copy(alpha = 0.2f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                tint = colorScheme.primary,
                                modifier = Modifier
                                    .size(20.dp)
                                    .rotate(rotation)
                            )
                        }
                    }

                    is UploadStatus.Queued -> {
                        Text(
                            text = "Na fila",
                            color = colorScheme.outline,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            if (status !is UploadStatus.Queued) {
                Spacer(modifier = Modifier.height(12.dp))

                val progress = if (status is UploadStatus.InProgress) status.progress else 1f
                val progressColor =
                    if (status is UploadStatus.Complete) Success else colorScheme.primary
                val progressLabel =
                    if (status is UploadStatus.Complete) "Concluído" else "${(progress * 100).toInt()}%"

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(4.dp)
                            .background(colorScheme.surfaceVariant, CircleShape)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progress)
                                .fillMaxHeight()
                                .background(
                                    if (status is UploadStatus.Complete) Brush.linearGradient(
                                        listOf(
                                            Success,
                                            Color(0xFF22C55E)
                                        )
                                    )
                                    else Brush.linearGradient(
                                        listOf(
                                            colorScheme.primary,
                                            colorScheme.secondary
                                        )
                                    ),
                                    CircleShape
                                )
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = progressLabel,
                        color = progressColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
