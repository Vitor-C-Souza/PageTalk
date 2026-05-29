package com.pagetalk.app.ui.presentation.library

import androidx.compose.ui.graphics.Brush

data class LibraryBook(
    val title: String,
    val author: String,
    val progress: Float,
    val coverGradient: Brush
)
