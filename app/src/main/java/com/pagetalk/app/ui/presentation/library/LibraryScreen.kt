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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToImport: () -> Unit = {},
) {
    var searchQuery by remember { mutableStateOf("") }
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
                    onValueChange = { searchQuery = it },
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
                    LibraryFilterChip(text = "Todos", isSelected = true)
                    LibraryFilterChip(text = "Em progresso", isSelected = false)
                    LibraryFilterChip(text = "Concluídos", isSelected = false)
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        bottomBar = {
            Column {
                LibraryStatsFooter()
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
        val books = listOf(
            LibraryBook(
                "Design Patterns",
                "Gang of Four",
                0.85f,
                Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFFB8A9FF)))
            ),
            LibraryBook(
                "Clean Code",
                "Robert Martin",
                0.60f,
                Brush.linearGradient(listOf(Color(0xFF9F8CFF), Color(0xFF7C5CFF)))
            ),
            LibraryBook(
                "Refactoring",
                "Martin Fowler",
                0.45f,
                Brush.linearGradient(listOf(Color(0xFFB8A9FF), Color(0xFF9F8CFF)))
            ),
            LibraryBook(
                "The Pragmatic",
                "Hunt & Thomas",
                0.92f,
                Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFF9F8CFF)))
            ),
            LibraryBook(
                "Code Complete",
                "Steve McConnell",
                0.30f,
                Brush.linearGradient(listOf(Color(0xFF9F8CFF), Color(0xFFB8A9FF)))
            ),
            LibraryBook(
                "Domain Driven",
                "Eric Evans",
                0.15f,
                Brush.linearGradient(listOf(Color(0xFFB8A9FF), Color(0xFF7C5CFF)))
            )
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(books) { book ->
                LibraryBookCard(book)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LibraryScreenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        LibraryScreen()
    }
}
