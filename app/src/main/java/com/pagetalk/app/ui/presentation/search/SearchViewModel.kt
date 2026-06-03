package com.pagetalk.app.ui.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.domain.usecase.GetAllBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

enum class SortBy {
    NEWEST, TITLE, PROGRESS
}

enum class SearchProgressFilter {
    ALL, IN_PROGRESS, COMPLETED
}

class SearchViewModel(
    getAllBooksUseCase: GetAllBooksUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _sortBy = MutableStateFlow(SortBy.NEWEST)
    val sortBy: StateFlow<SortBy> = _sortBy

    private val _progressFilter = MutableStateFlow(SearchProgressFilter.ALL)
    val progressFilter: StateFlow<SearchProgressFilter> = _progressFilter

    private val _allBooks = getAllBooksUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val searchResults: StateFlow<List<Book>> = combine(
        _searchQuery, _sortBy, _progressFilter, _allBooks
    ) { query, sort, filter, books ->
        if (query.isBlank()) {
            emptyList()
        } else {
            var filtered = books.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.author.contains(query, ignoreCase = true)
            }

            filtered = when (filter) {
                SearchProgressFilter.ALL -> filtered
                SearchProgressFilter.IN_PROGRESS -> filtered.filter { it.progress > 0f && it.progress < 1f }
                SearchProgressFilter.COMPLETED -> filtered.filter { it.progress >= 1f }
            }

            when (sort) {
                SortBy.NEWEST -> filtered.sortedByDescending { it.timestamp }
                SortBy.TITLE -> filtered.sortedBy { it.title }
                SortBy.PROGRESS -> filtered.sortedByDescending { it.progress }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun onSortByChange(newSort: SortBy) {
        _sortBy.value = newSort
    }

    fun onProgressFilterChange(newFilter: SearchProgressFilter) {
        _progressFilter.value = newFilter
    }
}
