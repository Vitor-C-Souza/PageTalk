package com.pagetalk.app.ui.presentation.importpdf.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
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
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pagetalk.app.ui.components.PrimaryButton

@Composable
fun UploadArea(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
            .drawBehind {
                // Purple Glow in the center
                drawCircle(
                    brush = Brush.radialGradient(
                        0.0f to colorScheme.primary.copy(alpha = 0.15f),
                        1.0f to Color.Transparent,
                    ),
                    radius = size.minDimension / 1.2f
                )

                // Dashed border
                drawRoundRect(
                    color = colorScheme.primary.copy(alpha = 0.3f),
                    style = stroke,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(32.dp.toPx())
                )
            }
            .background(
                brush = Brush.linearGradient(
                    listOf(colorScheme.surface, colorScheme.surfaceVariant)
                ),
                shape = RoundedCornerShape(32.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                listOf(colorScheme.primary, colorScheme.secondary)
                            ),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Upload,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Arraste um PDF aqui",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "ou toque para selecionar",
                color = colorScheme.outline,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = "Selecionar arquivo",
                onClick = onClick,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
