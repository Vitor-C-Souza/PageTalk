package com.pagetalk.app.ui.presentation.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.components.PageTalkLogo
import com.pagetalk.app.ui.theme.PageTalkTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary

    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        // Smooth Radial Glow (Canvas drawing to avoid rectangular artifacts)
        Spacer(
            modifier = Modifier
                .size(450.dp)
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            0.0f to primaryColor.copy(alpha = 0.45f),
                            0.4f to primaryColor.copy(alpha = 0.15f),
                            1.0f to Color.Transparent,
                        ),
                        radius = size.width / 2
                    )
                }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Logo larger and centered according to the image
            PageTalkLogo(
                size = 160.dp,
                iconSize = 90.dp
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "PageTalk",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 48.sp,
                    letterSpacing = (-1.5).sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Transforme PDFs em audiobooks",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = colorScheme.tertiary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        // Loading Dots at the bottom
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            LoadingDot(delay = 0, color = colorScheme.primary)
            LoadingDot(delay = 150, color = colorScheme.secondary)
            LoadingDot(delay = 300, color = colorScheme.tertiary)
        }
    }
}

@Composable
private fun LoadingDot(delay: Int, color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, delayMillis = delay, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .size(10.dp)
            .background(
                color = color.copy(alpha = alpha),
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        SplashScreen(onTimeout = {})
    }
}
