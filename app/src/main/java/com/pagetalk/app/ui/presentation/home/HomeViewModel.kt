package com.pagetalk.app.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.domain.usecase.GetAllBooksUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    getAllBooksUseCase: GetAllBooksUseCase
) : ViewModel() {

    // All books for recent documents
    val allBooks: StateFlow<List<Book>> = getAllBooksUseCase()
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    // Only books in progress for "Continue Listening"
    val continueListeningBooks: StateFlow<List<Book>> = allBooks
        .map { books -> books.filter { it.progress > 0f && it.progress < 1f } }
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    // Recent documents (limit to 5 for the list)
    val recentBooks: StateFlow<List<Book>> = allBooks
        .map { it.take(5) }
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())
}