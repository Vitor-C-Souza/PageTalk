package com.pagetalk.app.ui.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.components.BottomNavItem
import com.pagetalk.app.ui.components.BottomNavigation
import com.pagetalk.app.ui.components.HeaderCustom
import com.pagetalk.app.ui.components.InputCustom
import com.pagetalk.app.ui.navigation.Screen
import com.pagetalk.app.ui.presentation.search.components.SearchResultItem
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToLibrary: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("Design Patterns") }
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorScheme.background,
        topBar = {
            Column {
                HeaderCustom(
                    title = "Buscar"
                )

                // Search Bar
                InputCustom(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Filter Button
                Surface(
                    onClick = { /* TODO: Filters */ },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.05f),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        colorScheme.primary.copy(alpha = 0.2f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null,
                            tint = colorScheme.tertiary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Filtros avançados",
                            color = colorScheme.tertiary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        },
        bottomBar = {
            BottomNavigation(
                currentScreen = Screen.Search,
                onNavItemClick = { item ->
                    when (item) {
                        BottomNavItem.Home -> onNavigateToHome()
                        BottomNavItem.Library -> onNavigateToLibrary()
                        BottomNavItem.Profile -> onNavigateToProfile()
                        BottomNavItem.Search -> { /* Already here */
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Results Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "5 resultados encontrados",
                        color = colorScheme.outline,
                        fontSize = 14.sp
                    )
                    TextButton(onClick = { /* TODO: Sort */ }) {
                        Text(
                            text = "Ordenar",
                            color = colorScheme.primary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Search Results
            val results = listOf(
                SearchResultData("Design Patterns", "Gang of Four", 0.85f, "Título exato"),
                SearchResultData("Design Systems", "Alla Kholmatova", 0.45f, "Título semelhante"),
                SearchResultData("Refactoring: Design", "Martin Fowler", 0.60f, "No título"),
                SearchResultData("Clean Architecture", "Robert Martin", 0.30f, "Conteúdo"),
                SearchResultData("Domain Driven Design", "Eric Evans", 0.15f, "No título")
            )

            itemsIndexed(results) { index, result ->
                val gradient = when (index % 5) {
                    0 -> Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFFB8A9FF)))
                    1 -> Brush.linearGradient(listOf(Color(0xFF9F8CFF), Color(0xFF7C5CFF)))
                    2 -> Brush.linearGradient(listOf(Color(0xFFB8A9FF), Color(0xFF9F8CFF)))
                    3 -> Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFF9F8CFF)))
                    else -> Brush.linearGradient(listOf(Color(0xFF9F8CFF), Color(0xFFB8A9FF)))
                }

                SearchResultItem(
                    title = result.title,
                    author = result.author,
                    progress = result.progress,
                    match = result.match,
                    isBestMatch = index == 0,
                    iconGradient = gradient,
                    onClick = { /* TODO: Navigate to player */ }
                )
            }

            // Recent Searches section
            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.05f))
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Buscas recentes",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { /* TODO: Clear */ }) {
                        Text(
                            text = "Limpar",
                            color = colorScheme.outline,
                            fontSize = 13.sp
                        )
                    }
                }

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Clean Code", "Refactoring", "Architecture").forEach { term ->
                        SuggestionChip(
                            onClick = { searchQuery = term },
                            label = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.AccessTime,
                                        null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(Modifier.width(4.dp))
                                    Text(term)
                                }
                            },
                            shape = CircleShape,
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = Color.White.copy(alpha = 0.05f),
                                labelColor = colorScheme.onSurfaceVariant
                            ),
                            border = SuggestionChipDefaults.suggestionChipBorder(
                                borderColor = Color.White.copy(alpha = 0.1f),
                                enabled = true
                            )
                        )
                    }
                }
            }
        }
    }
}

data class SearchResultData(
    val title: String,
    val author: String,
    val progress: Float,
    val match: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchScreenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        SearchScreen()
    }
}
