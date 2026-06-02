package com.pagetalk.app.domain.model

data class Book(
    val id: Long = 0,
    val title: String,
    val author: String,
    val uri: String,
    val size: String,
    val pages: Int,
    val progress: Float = 0f,
    val timestamp: Long = System.currentTimeMillis()
)
