package com.pagetalk.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun PageTalkBookCard(
    title: String,
    author: String,
    progress: Float, // 0.0 to 1.0
    modifier: Modifier = Modifier,
    coverGradient: Brush = Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFFB8A9FF))),
    onClick: () -> Unit = {},
) {
    val colorScheme = MaterialTheme.colorScheme

    // Card Container Gradient
    val cardGradient = Brush.linearGradient(
        colors = listOf(colorScheme.surface, colorScheme.surfaceVariant)
    )

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(24.dp)
            ),
        color = Color.Transparent,
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .background(cardGradient)
                .padding(24.dp)
        ) {
            // Book Cover
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 4f)
                    .graphicsLayer {
                        shadowElevation = 16.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = false
                        this.ambientShadowColor = Color(0xFF7C5CFF).copy(alpha = 0.2f)
                        this.spotShadowColor = Color(0xFF7C5CFF).copy(alpha = 0.2f)
                    }
                    .background(coverGradient, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.LibraryBooks,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color.White.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title and Author
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 16.sp
                )
            )

            Text(
                text = author,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = colorScheme.outline,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Progress Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Custom Progress Bar
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .background(colorScheme.surfaceVariant, shape = RoundedCornerShape(100.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .fillMaxHeight()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(colorScheme.primary, colorScheme.secondary)
                                ),
                                shape = RoundedCornerShape(100.dp)
                            )
                    )
                }

                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = colorScheme.tertiary,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1115)
@Composable
fun CardsPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .width(280.dp)
            ) {
                Text(
                    text = "Cards",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                PageTalkBookCard(
                    title = "Título do Livro",
                    author = "Autor",
                    progress = 0.75f,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                PageTalkBookCard(
                    title = "Outro Documento",
                    author = "Autor",
                    progress = 0.33f,
                    coverGradient = Brush.linearGradient(
                        listOf(
                            Color(0xFF9F8CFF),
                            Color(0xFF7C5CFF)
                        )
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
