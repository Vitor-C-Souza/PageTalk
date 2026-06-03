package com.pagetalk.app.ui.presentation.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.domain.usecase.GetAllBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LibraryViewModel(
    getAllBooksUseCase: GetAllBooksUseCase,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filter = MutableStateFlow(LibraryFilter.ALL)
    val filter: StateFlow<LibraryFilter> = _filter

    private val _allBooks = getAllBooksUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val libraryStats: StateFlow<LibraryStats> = _allBooks.map { books ->
        LibraryStats(
            total = books.size,
            reading = books.count { (it.progress > 0f) && (it.progress < 1f) },
            completed = books.count { it.progress >= 1f }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LibraryStats())

    val filteredBooks: StateFlow<List<Book>> =
        combine(_allBooks, _searchQuery, _filter) { books, query, filter ->
            books.filter { book ->
                val matchesQuery = book.title.contains(query, ignoreCase = true) ||
                        book.author.contains(query, ignoreCase = true)
                val matchesFilter = when (filter) {
                    LibraryFilter.ALL -> true
                    LibraryFilter.IN_PROGRESS -> book.progress > 0f && book.progress < 1f
                    LibraryFilter.COMPLETED -> book.progress >= 1f
                }
                matchesQuery && matchesFilter
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onFilterChange(newFilter: LibraryFilter) {
        _filter.value = newFilter
    }
}

enum class LibraryFilter {
    ALL, IN_PROGRESS, COMPLETED
}

data class LibraryStats(
    val total: Int = 0,
    val reading: Int = 0,
    val completed: Int = 0
)
