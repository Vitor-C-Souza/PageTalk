package com.pagetalk.app.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pagetalk.app.domain.model.Book
import com.pagetalk.app.ui.components.BottomNavItem
import com.pagetalk.app.ui.components.BottomNavigation
import com.pagetalk.app.ui.components.HeaderCustom
import com.pagetalk.app.ui.navigation.Screen
import com.pagetalk.app.ui.presentation.home.components.ContinueListen
import com.pagetalk.app.ui.presentation.home.components.RecentDocuments
import com.pagetalk.app.ui.theme.PageTalkTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToImport: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToLibrary: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToPlayer: (Long) -> Unit = {},
    viewModel: HomeViewModel = koinViewModel()
) {
    val continueListeningBooks by viewModel.continueListeningBooks.collectAsState()
    val recentBooks by viewModel.recentBooks.collectAsState()

    HomeScreenContent(
        modifier = modifier,
        onNavigateToImport = onNavigateToImport,
        onNavigateToSearch = onNavigateToSearch,
        onNavigateToLibrary = onNavigateToLibrary,
        onNavigateToProfile = onNavigateToProfile,
        onNavigateToPlayer = onNavigateToPlayer,
        continueListeningBooks = continueListeningBooks,
        recentBooks = recentBooks
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onNavigateToImport: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToLibrary: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToPlayer: (Long) -> Unit = {},
    continueListeningBooks: List<Book> = emptyList(),
    recentBooks: List<Book> = emptyList()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HeaderCustom(
                title = "Início",
                subtitle = "Continue de onde parou"
            )
        },
        bottomBar = {
            BottomNavigation(
                currentScreen = Screen.Home,
                onNavItemClick = { item ->
                    when (item) {
                        BottomNavItem.Search -> onNavigateToSearch()
                        BottomNavItem.Library -> onNavigateToLibrary()
                        BottomNavItem.Profile -> onNavigateToProfile()
                        else -> { /* TODO: Handle others */
                        }
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Transparent),
        ) {
            item {
                ContinueListen(
                    onAddClick = onNavigateToImport,
                    onBookClick = onNavigateToPlayer,
                    books = continueListeningBooks
                )
            }
            item {
                RecentDocuments(
                    books = recentBooks,
                    onBookClick = onNavigateToPlayer
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {

    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        HomeScreenContent(
            continueListeningBooks = listOf(
                Book(
                    id = 1,
                    title = "Livro 1",
                    author = "Autor 1",
                    progress = 0.45f,
                    uri = "",
                    size = "2MB",
                    pages = 100
                ),
                Book(
                    id = 2,
                    title = "Livro 2",
                    author = "Autor 2",
                    progress = 0.80f,
                    uri = "",
                    size = "1.5MB",
                    pages = 120
                )
            ),
            recentBooks = listOf(
                Book(
                    id = 3,
                    title = "Documento 1",
                    author = "Autor A",
                    progress = 0f,
                    uri = "",
                    size = "500KB",
                    pages = 10
                ),
                Book(
                    id = 4,
                    title = "Documento 2",
                    author = "Autor B",
                    progress = 0f,
                    uri = "",
                    size = "750KB",
                    pages = 15
                ),
                Book(
                    id = 5,
                    title = "Documento 3",
                    author = "Autor C",
                    progress = 0f,
                    uri = "",
                    size = "1MB",
                    pages = 20
                )
            )
        )
    }
}
