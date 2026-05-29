package com.pagetalk.app.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pagetalk.app.ui.components.BottomNavItem
import com.pagetalk.app.ui.components.BottomNavigation
import com.pagetalk.app.ui.components.HeaderCustom
import com.pagetalk.app.ui.navigation.Screen
import com.pagetalk.app.ui.presentation.home.components.ContinueListen
import com.pagetalk.app.ui.presentation.home.components.RecentDocuments
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToImport: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToLibrary: () -> Unit = {}
) {
    HomeScreenContent(modifier, onNavigateToImport, onNavigateToSearch, onNavigateToLibrary)
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onNavigateToImport: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToLibrary: () -> Unit = {}
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
                ContinueListen(onAddClick = onNavigateToImport)
            }
            item {
                RecentDocuments()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        HomeScreenContent()
    }
}
