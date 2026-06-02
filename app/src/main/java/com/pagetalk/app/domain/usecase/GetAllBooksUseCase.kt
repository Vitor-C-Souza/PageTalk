package com.pagetalk.app.domain.usecase

import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetAllBooksUseCase(private val repository: BookRepository) {
    operator fun invoke(): Flow<List<Book>> = repository.getAllBooks()
}
