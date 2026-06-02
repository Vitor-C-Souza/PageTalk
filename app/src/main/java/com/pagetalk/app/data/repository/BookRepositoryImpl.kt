package com.pagetalk.app.data.repository

import com.pagetalk.app.data.local.BookDao
import com.pagetalk.app.data.local.toDomain
import com.pagetalk.app.data.local.toEntity
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(private val bookDao: BookDao) : BookRepository {
    override fun getAllBooks(): Flow<List<Book>> {
        return bookDao.getAllBooks().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertBook(book: Book): Long {
        return bookDao.insertBook(book.toEntity())
    }

    override suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book.toEntity())
    }

    override suspend fun getBookById(id: Long): Book? {
        return bookDao.getBookById(id)?.toDomain()
    }
}
