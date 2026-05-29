package com.pagetalk.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun PageTalkPlayerControls(
    onSkipBack: () -> Unit,
    onPlayPause: () -> Unit,
    onSkipForward: () -> Unit,
    isPlaying: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Skip Back Button
        PageTalkSecondaryIconButton(
            icon = Icons.Default.SkipPrevious,
            onClick = onSkipBack,
            modifier = Modifier.size(56.dp)
        )

        Spacer(modifier = Modifier.width(24.dp))

        // Play/Pause Button
        PageTalkPrimaryRoundButton(
            icon = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
            onClick = onPlayPause,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.width(24.dp))

        // Skip Forward Button
        PageTalkSecondaryIconButton(
            icon = Icons.Default.SkipNext,
            onClick = onSkipForward,
            modifier = Modifier.size(56.dp)
        )
    }
}

@Composable
fun PageTalkSecondaryIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colorScheme = MaterialTheme.colorScheme

    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .background(
                color = colorScheme.onSurface.copy(alpha = 0.05f),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = colorScheme.primary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colorScheme.onSurface,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun PageTalkPrimaryRoundButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryGradient = Brush.horizontalGradient(
        colors = listOf(colorScheme.primary, colorScheme.secondary)
    )

    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .graphicsLayer {
                if (enabled) {
                    shadowElevation = 12.dp.toPx()
                    shape = CircleShape
                    clip = false
                    this.ambientShadowColor = colorScheme.primary
                    this.spotShadowColor = colorScheme.primary
                }
            },
        shape = CircleShape,
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryGradient),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorScheme.onPrimary,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1115)
@Composable
fun PlayerControlsPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Player Controls",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                PageTalkPlayerControls(
                    onSkipBack = {},
                    onPlayPause = {},
                    onSkipForward = {}
                )
            }
        }
    }
}
