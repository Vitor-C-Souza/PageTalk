package com.pagetalk.app.ui.presentation.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.ui.components.BottomNavItem
import com.pagetalk.app.ui.components.BottomNavigation
import com.pagetalk.app.ui.components.HeaderCustom
import com.pagetalk.app.ui.components.InputCustom
import com.pagetalk.app.ui.components.PageTalkFAB
import com.pagetalk.app.ui.navigation.Screen
import com.pagetalk.app.ui.presentation.library.components.LibraryBookCard
import com.pagetalk.app.ui.presentation.library.components.LibraryFilterChip
import com.pagetalk.app.ui.presentation.library.components.LibraryStatsFooter
import com.pagetalk.app.ui.presentation.library.components.LibraryToggleButton
import com.pagetalk.app.ui.theme.PageTalkTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToImport: () -> Unit = {},
    onNavigateToPlayer: (Long) -> Unit = {},
    viewModel: LibraryViewModel = koinViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filter by viewModel.filter.collectAsState()
    val filteredBooks by viewModel.filteredBooks.collectAsState()
    val stats by viewModel.libraryStats.collectAsState()

    LibraryScreenContent(
        searchQuery = searchQuery,
        filter = filter,
        filteredBooks = filteredBooks,
        stats = stats,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onFilterChange = viewModel::onFilterChange,
        onNavigateToHome = onNavigateToHome,
        onNavigateToSearch = onNavigateToSearch,
        onNavigateToProfile = onNavigateToProfile,
        onNavigateToImport = onNavigateToImport,
        onBookClick = onNavigateToPlayer,
        modifier = modifier
    )
}

@Composable
fun LibraryScreenContent(
    searchQuery: String,
    filter: LibraryFilter,
    filteredBooks: List<Book>,
    stats: LibraryStats,
    onSearchQueryChange: (String) -> Unit,
    onFilterChange: (LibraryFilter) -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToImport: () -> Unit,
    onBookClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorScheme.background,
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeaderCustom(title = "Biblioteca")

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 44.dp)
                    ) {
                        LibraryToggleButton(icon = Icons.Default.GridView, isSelected = true)
                        LibraryToggleButton(
                            icon = Icons.AutoMirrored.Filled.List,
                            isSelected = false
                        )
                    }
                }

                InputCustom(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = "Buscar na biblioteca...",
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Filter Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LibraryFilterChip(
                        text = "Todos",
                        isSelected = filter == LibraryFilter.ALL,
                        onClick = { onFilterChange(LibraryFilter.ALL) }
                    )
                    LibraryFilterChip(
                        text = "Em progresso",
                        isSelected = filter == LibraryFilter.IN_PROGRESS,
                        onClick = { onFilterChange(LibraryFilter.IN_PROGRESS) }
                    )
                    LibraryFilterChip(
                        text = "Concluídos",
                        isSelected = filter == LibraryFilter.COMPLETED,
                        onClick = { onFilterChange(LibraryFilter.COMPLETED) }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        bottomBar = {
            Column {
                LibraryStatsFooter(
                    total = stats.total,
                    reading = stats.reading,
                    completed = stats.completed
                )
                BottomNavigation(
                    currentScreen = Screen.Library,
                    onNavItemClick = { item ->
                        when (item) {
                            BottomNavItem.Home -> onNavigateToHome()
                            BottomNavItem.Search -> onNavigateToSearch()
                            BottomNavItem.Profile -> onNavigateToProfile()
                            BottomNavItem.Library -> { /* Already here */
                            }
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            PageTalkFAB(
                onClick = onNavigateToImport,
                modifier = Modifier.padding(bottom = 80.dp)
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredBooks, key = { it.id }) { book ->
                LibraryBookCard(
                    book = book,
                    onClick = { onBookClick(book.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LibraryScreenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        LibraryScreenContent(
            searchQuery = "",
            filter = LibraryFilter.ALL,
            filteredBooks = listOf(
                Book(
                    id = 1,
                    title = "O Senhor dos Anéis",
                    author = "J.R.R. Tolkien",
                    uri = "",
                    size = "1MB",
                    pages = 1000,
                    progress = 0.5f
                ),
                Book(
                    id = 2,
                    title = "Harry Potter",
                    author = "J.K. Rowling",
                    uri = "",
                    size = "1MB",
                    pages = 500,
                    progress = 1.0f
                )
            ),
            stats = LibraryStats(total = 2, reading = 1, completed = 1),
            onSearchQueryChange = {},
            onFilterChange = {},
            onNavigateToHome = {},
            onNavigateToSearch = {},
            onNavigateToProfile = {},
            onNavigateToImport = {},
            onBookClick = {}
        )
    }
}
