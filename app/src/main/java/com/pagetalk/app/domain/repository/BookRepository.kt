package com.pagetalk.app.domain.repository

import com.pagetalk.app.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getAllBooks(): Flow<List<Book>>
    suspend fun insertBook(book: Book): Long
    suspend fun deleteBook(book: Book)
    suspend fun getBookById(id: Long): Book?
}
