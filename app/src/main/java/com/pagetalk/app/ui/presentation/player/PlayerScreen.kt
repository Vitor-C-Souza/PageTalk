package com.pagetalk.app.ui.presentation.player

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.components.PlayerControls
import com.pagetalk.app.ui.theme.PageTalkTheme
import kotlin.random.Random

@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onMore: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F1115))
    ) {
        // --- Background Effects ---
        // Blurred gradient top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7C5CFF).copy(alpha = 0.3f),
                            Color(0xFF0F1115).copy(alpha = 0f)
                        )
                    )
                )
        )

        // Floating glow behind cover
        Box(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
                .graphicsLayer {
                    alpha = 0.3f
                }
                .blur(120.dp)
                .background(Color(0xFF7C5CFF), CircleShape)
        )

        // --- Content ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White.copy(alpha = 0.05f), CircleShape)
                        .border(1.dp, Color.White.copy(alpha = 0.1f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }

                IconButton(
                    onClick = onMore,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White.copy(alpha = 0.05f), CircleShape)
                        .border(1.dp, Color.White.copy(alpha = 0.1f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Mais",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cover Art
            Box(
                modifier = Modifier
                    .size(width = 256.dp, height = 300.dp)
                    .graphicsLayer {
                        shadowElevation = 50.dp.toPx()
                        shape = RoundedCornerShape(32.dp)
                        clip = false
                        this.spotShadowColor = Color(0xFF7C5CFF)
                        this.ambientShadowColor = Color(0xFF7C5CFF)
                    }
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF7C5CFF), Color(0xFFB8A9FF))
                        ),
                        RoundedCornerShape(32.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Glassmorphism Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0.05f))
                        .blur(8.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Título do Livro",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Track Info
            Text(
                text = "Título do Livro",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Nome do Autor",
                color = Color(0xFFB8A9FF),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Quick Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Favorite */ }) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favoritar",
                        tint = Color(0xFFB8A9FF),
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = { /* Share */ }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Compartilhar",
                        tint = Color(0xFFB8A9FF),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Waveform
            WaveformDisplay(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                progress = 0.6f
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Progress and Time
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(Color(0xFF1E222B), CircleShape)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF7C5CFF), Color(0xFF9F8CFF))
                                ),
                                CircleShape
                            )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "12:34", color = Color(0xFF7B8190), fontSize = 12.sp)
                    Text(text = "21:00", color = Color(0xFF7B8190), fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main Controls
            PlayerControls(
                onSkipBack = { /* Skip -10s */ },
                onPlayPause = { /* Play/Pause */ },
                onSkipForward = { /* Skip +10s */ },
                isPlaying = false,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Volume and Speed
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width(120.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = null,
                        tint = Color(0xFFB8A9FF),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(4.dp)
                            .background(Color(0xFF1E222B), CircleShape)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .fillMaxHeight()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF7C5CFF), Color(0xFF9F8CFF))
                                    ),
                                    CircleShape
                                )
                        )
                    }
                }

                Button(
                    onClick = { /* Change Speed */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.05f),
                        contentColor = Color(0xFFB8A9FF)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        Color.White.copy(alpha = 0.1f)
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text(text = "1.0x", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun WaveformDisplay(
    modifier: Modifier = Modifier,
    progress: Float = 0.5f,
    barCount: Int = 40
) {
    val barHeights = remember { List(barCount) { (Random.nextFloat() * 0.8f) + 0.2f } }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        barHeights.forEachIndexed { index, heightFactor ->
            val isActive = (index.toFloat() / barCount) < progress
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(heightFactor)
                    .clip(CircleShape)
                    .background(
                        if (isActive) {
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFFB8A9FF), Color(0xFF7C5CFF))
                            )
                        } else {
                            Brush.linearGradient(listOf(Color(0xFF1E222B), Color(0xFF1E222B)))
                        }
                    )
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7")
@Composable
fun PlayerScreenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        PlayerScreen()
    }
}
