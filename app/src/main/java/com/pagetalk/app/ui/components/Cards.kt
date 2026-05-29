package com.pagetalk.app.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.theme.PageTalkTheme


@Composable
fun AddBookCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val stroke =
        Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))

    Column(
        modifier = modifier
            .width(160.dp)
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4.5f)
                .clickable(onClick = onClick)
                .drawBehind {
                    drawRoundRect(
                        color = Color.White.copy(alpha = 0.1f),
                        style = stroke,
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(24.dp.toPx())
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    modifier = Modifier.size(56.dp),
                    shape = CircleShape,
                    color = colorScheme.primary,
                    tonalElevation = 8.dp
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Adicionar",
                    color = colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Novo PDF",
            style = MaterialTheme.typography.bodyLarge,
            color = colorScheme.outline,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun BookCard(
    title: String,
    author: String,
    progress: Float, // 0.0 to 1.0
    modifier: Modifier = Modifier,
    coverGradient: Brush = Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFFB8A9FF))),
    onClick: () -> Unit = {},
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = modifier
            .width(160.dp)
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Book Cover
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4.5f)
                .clickable(onClick = onClick)
                .graphicsLayer {
                    shadowElevation = 8.dp.toPx()
                    shape = RoundedCornerShape(24.dp)
                    clip = true
                    this.ambientShadowColor = Color(0xFF7C5CFF).copy(alpha = 0.2f)
                    this.spotShadowColor = Color(0xFF7C5CFF).copy(alpha = 0.2f)
                }
                .background(coverGradient, shape = RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = Color.White.copy(alpha = 0.8f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            ),
            maxLines = 1
        )

        Text(
            text = author,
            style = MaterialTheme.typography.bodySmall.copy(
                color = colorScheme.outline,
                fontSize = 12.sp
            ),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

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
                    color = colorScheme.outline,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun RecentDocumentItem(
    title: String,
    author: String,
    progress: Float,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme

    Surface(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = colorScheme.surfaceVariant.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Document Icon Box
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(colorScheme.primary, colorScheme.secondary)
                        ),
                        shape = RoundedCornerShape(32.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    color = Color.White
                )

                Text(
                    text = author,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.outline
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Progress row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .background(
                                Color.White.copy(alpha = 0.05f),
                                shape = RoundedCornerShape(100.dp)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progress)
                                .fillMaxHeight()
                                .background(colorScheme.primary, shape = RoundedCornerShape(100.dp))
                        )
                    }

                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = colorScheme.outline,
                            fontSize = 12.sp
                        )
                    )
                }
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

                BookCard(
                    title = "Título do Livro",
                    author = "Autor",
                    progress = 0.75f,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                BookCard(
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
