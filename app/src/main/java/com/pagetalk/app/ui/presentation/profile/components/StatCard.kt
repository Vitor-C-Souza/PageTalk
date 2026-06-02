package com.pagetalk.app.ui.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatCard(
    label: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    // Smooth background gradient matching the card style
    val cardBackground = Brush.linearGradient(
        colors = listOf(
            colorScheme.surface,
            colorScheme.surfaceVariant.copy(alpha = 0.8f)
        )
    )

    Surface(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(24.dp),
        color = Color.Transparent,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(cardBackground)
                .drawBehind {
                    // Smooth, large radial gradient for a natural "glow" from the corner
                    drawCircle(
                        brush = Brush.radialGradient(
                            0.0f to color.copy(alpha = 0.15f),
                            0.5f to color.copy(alpha = 0.05f),
                            1.0f to Color.Transparent,
                        ),
                        radius = size.maxDimension * 0.8f,
                        center = androidx.compose.ui.geometry.Offset(
                            size.width * 0.9f,
                            size.height * 0.2f
                        )
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Icon with subtle background - matching the clean look of image 2
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    color = color.copy(alpha = 0.15f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Column {
                    Text(
                        text = value,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = label,
                        color = colorScheme.outline,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
