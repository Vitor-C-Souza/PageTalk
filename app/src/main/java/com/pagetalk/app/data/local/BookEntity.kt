package com.pagetalk.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val author: String,
    val uri: String,
    val size: String,
    val pages: Int,
    val progress: Float = 0f,
    val timestamp: Long = System.currentTimeMillis()
)
