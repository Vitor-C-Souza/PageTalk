package com.pagetalk.app.domain.usecase

import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.domain.repository.BookRepository

class UpdateBookUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(book: Book): Long = repository.insertBook(book)
}
