package com.pagetalk.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageTalkFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = modifier
            .size(100.dp), // Larger area for the glow
        contentAlignment = Alignment.Center
    ) {
        // Vibrant Radial Glow
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            0.0f to colorScheme.primary.copy(alpha = 0.5f),
                            0.4f to colorScheme.primary.copy(alpha = 0.2f),
                            1.0f to Color.Transparent,
                        ),
                        radius = size.minDimension / 2
                    )
                }
        )

        FloatingActionButton(
            onClick = onClick,
            containerColor = Color.Transparent,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .size(64.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(colorScheme.primary, colorScheme.secondary)
                    ),
                    shape = CircleShape
                ),
            elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Adicionar",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
