package com.pagetalk.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun PageTalkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
    val outlineColor = MaterialTheme.colorScheme.outline
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary

    val primaryGradient = Brush.horizontalGradient(
        colors = listOf(primaryColor, secondaryColor)
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .height(52.dp)
            .graphicsLayer {
                if (enabled) {
                    shadowElevation = 8.dp.toPx()
                    shape = RoundedCornerShape(16.dp)
                    clip = false
                    this.ambientShadowColor = primaryColor
                    this.spotShadowColor = primaryColor
                }
            },
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = onPrimaryColor,
            disabledContainerColor = surfaceVariantColor,
            disabledContentColor = outlineColor
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (enabled) Modifier.background(primaryGradient)
                    else Modifier.background(surfaceVariantColor)
                )
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
            )
        }
    }
}

@Composable
fun PageTalkSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val outlineColor = MaterialTheme.colorScheme.outline

    Button(
        onClick = onClick,
        modifier = modifier
            .height(52.dp)
            .border(
                width = 1.dp,
                color = if (enabled) primaryColor.copy(alpha = 0.3f)
                else onSurfaceColor.copy(alpha = 0.05f),
                shape = RoundedCornerShape(16.dp)
            ),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = onSurfaceColor.copy(alpha = 0.05f),
            contentColor = if (enabled) onSurfaceColor else outlineColor,
            disabledContainerColor = onSurfaceColor.copy(alpha = 0.05f),
            disabledContentColor = outlineColor
        ),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )
        )
    }
}

@Composable
fun PageTalkIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentDescription: String? = null,
    isPrimary: Boolean = true
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val outlineColor = MaterialTheme.colorScheme.outline
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary

    val primaryGradient = Brush.horizontalGradient(
        colors = listOf(primaryColor, secondaryColor)
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .size(52.dp)
            .then(
                if (isPrimary && enabled) Modifier.graphicsLayer {
                    shadowElevation = 8.dp.toPx()
                    shape = RoundedCornerShape(16.dp)
                    clip = false
                    this.ambientShadowColor = primaryColor
                    this.spotShadowColor = primaryColor
                } else if (!isPrimary) Modifier.border(
                    width = 1.dp,
                    color = if (enabled) primaryColor.copy(alpha = 0.3f)
                    else onSurfaceColor.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(16.dp)
                ) else Modifier
            ),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isPrimary) Color.Transparent else onSurfaceColor.copy(alpha = 0.05f),
            contentColor = if (enabled) {
                if (isPrimary) onPrimaryColor else onSurfaceColor
            } else outlineColor,
            disabledContainerColor = if (isPrimary) surfaceVariantColor else onSurfaceColor.copy(
                alpha = 0.05f
            ),
            disabledContentColor = outlineColor
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (isPrimary && enabled) Modifier.background(primaryGradient)
                    else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1115)
@Composable
fun ButtonsPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        val colorScheme = MaterialTheme.colorScheme
        Surface(color = colorScheme.background) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Primary Column
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "Primary",
                            color = colorScheme.tertiary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        PageTalkButton(
                            text = "Normal",
                            onClick = {},
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        // Pressed simulation: using secondary/tertiary for darker/shifted gradient
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .height(52.dp)
                                .fillMaxWidth()
                                .graphicsLayer {
                                    shadowElevation = 12.dp.toPx()
                                    shape = RoundedCornerShape(16.dp)
                                    clip = false
                                    this.ambientShadowColor = colorScheme.primary
                                    this.spotShadowColor = colorScheme.primary
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = colorScheme.onPrimary
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.horizontalGradient(
                                            listOf(
                                                colorScheme.secondary,
                                                colorScheme.tertiary
                                            )
                                        )
                                    ), contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "Pressed",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 15.sp
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        PageTalkButton(
                            text = "Disabled",
                            onClick = {},
                            enabled = false,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    // Secondary Column
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "Secondary",
                            color = colorScheme.tertiary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        PageTalkSecondaryButton(
                            text = "Normal",
                            onClick = {},
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        // Pressed simulation
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .height(52.dp)
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    colorScheme.primary.copy(alpha = 0.5f),
                                    RoundedCornerShape(16.dp)
                                ),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorScheme.onSurface.copy(alpha = 0.1f),
                                contentColor = colorScheme.onSurface
                            )
                        ) {
                            Text(
                                "Pressed",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        PageTalkSecondaryButton(
                            text = "Disabled",
                            onClick = {},
                            enabled = false,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    // Icon Column
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "Icon",
                            color = colorScheme.tertiary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        PageTalkIconButton(
                            icon = Icons.Default.PlayArrow,
                            onClick = {},
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        PageTalkIconButton(
                            icon = Icons.Default.Pause,
                            onClick = {},
                            isPrimary = false,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        PageTalkIconButton(
                            icon = Icons.Default.PlayArrow,
                            onClick = {},
                            enabled = false,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
