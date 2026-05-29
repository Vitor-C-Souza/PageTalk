package com.pagetalk.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun PageTalkLogo(
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    iconSize: Dp = 56.dp,
    showShadow: Boolean = true
) {
    val colorScheme = MaterialTheme.colorScheme

    val logoGradient = Brush.linearGradient(
        colors = listOf(colorScheme.primary, colorScheme.tertiary)
    )

    Box(
        modifier = modifier
            .size(size)
            .then(
                if (showShadow) {
                    Modifier.graphicsLayer {
                        shadowElevation = 12.dp.toPx()
                        shape = RoundedCornerShape(24.dp)
                        clip = false
                        this.ambientShadowColor = colorScheme.primary
                        this.spotShadowColor = colorScheme.primary
                    }
                } else Modifier
            )
            .background(
                brush = logoGradient,
                shape = RoundedCornerShape(24.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        val logoIcon = getPageTalkLogoIcon()
        Icon(
            imageVector = logoIcon,
            contentDescription = "PageTalk Logo",
            modifier = Modifier.size(iconSize),
            tint = Color.White
        )
    }
}

fun getPageTalkLogoIcon(): ImageVector {
    return ImageVector.Builder(
        name = "PageTalkLogoIcon",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).group {
        // Document path (SVG: M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z)
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(9f, 12f)
            horizontalLineTo(15f)
            moveTo(9f, 16f)
            horizontalLineTo(15f)
            moveTo(19f, 21f)
            horizontalLineTo(7f)
            curveTo(5.8954f, 21f, 5f, 20.1046f, 5f, 19f)
            verticalLineTo(5f)
            curveTo(5f, 3.8954f, 5.8954f, 3f, 7f, 3f)
            horizontalLineTo(12.586f)
            curveTo(12.8512f, 3f, 13.1056f, 3.1054f, 13.293f, 3.293f)
            lineTo(18.707f, 8.707f)
            curveTo(18.8946f, 8.8946f, 19f, 9.1488f, 19f, 9.414f)
            verticalLineTo(19f)
            curveTo(19f, 20.1046f, 18.1046f, 21f, 19f, 21f)
            close()
        }
        // Play button path (SVG: M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z)
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(14.752f, 11.168f)
            lineTo(11.555f, 9.036f)
            curveTo(11.168f, 8.778f, 10.647f, 9.055f, 10.647f, 9.521f)
            verticalLineTo(13.784f)
            curveTo(10.647f, 14.25f, 11.168f, 14.527f, 11.555f, 14.269f)
            lineTo(14.752f, 12.137f)
            curveTo(15.083f, 11.916f, 15.083f, 11.389f, 14.752f, 11.168f)
            close()
        }
    }.build()
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1115)
@Composable
fun LogoPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(48.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                PageTalkLogo()
            }
        }
    }
}
