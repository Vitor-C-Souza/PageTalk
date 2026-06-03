package com.pagetalk.app.ui.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToLibrary: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToPlayer: (Long) -> Unit = {},
    viewModel: SearchViewModel = koinViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val sortBy by viewModel.sortBy.collectAsState()
    val progressFilter by viewModel.progressFilter.collectAsState()
    
    val colorScheme = MaterialTheme.colorScheme
    var showFilters by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

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
                    onValueChange = { viewModel.onSearchQueryChange(it) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Filter Button
                Surface(
                    onClick = { showFilters = true },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    shape = CircleShape,
                    color = if (progressFilter != SearchProgressFilter.ALL || sortBy != SortBy.NEWEST)
                        colorScheme.primary.copy(alpha = 0.1f)
                    else Color.White.copy(alpha = 0.05f),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (progressFilter != SearchProgressFilter.ALL || sortBy != SortBy.NEWEST)
                            colorScheme.primary.copy(alpha = 0.4f)
                        else colorScheme.primary.copy(alpha = 0.2f)
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
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            if (searchQuery.isBlank()) {
                // Empty state or instructions
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = null,
                        tint = colorScheme.outline.copy(alpha = 0.2f),
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Digite algo para buscar",
                        color = colorScheme.outline,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${searchResults.size} resultados encontrados",
                                color = colorScheme.outline,
                                fontSize = 14.sp
                            )
                        }
                    }

                    itemsIndexed(searchResults) { index, result ->
                        val gradient = when (index % 5) {
                            0 -> Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFFB8A9FF)))
                            1 -> Brush.linearGradient(listOf(Color(0xFF9F8CFF), Color(0xFF7C5CFF)))
                            2 -> Brush.linearGradient(listOf(Color(0xFFB8A9FF), Color(0xFF9F8CFF)))
                            3 -> Brush.linearGradient(listOf(Color(0xFF7C5CFF), Color(0xFF9F8CFF)))
                            else -> Brush.linearGradient(
                                listOf(
                                    Color(0xFF9F8CFF),
                                    Color(0xFFB8A9FF)
                                )
                            )
                        }

                        SearchResultItem(
                            title = result.title,
                            author = result.author,
                            progress = result.progress,
                            match = "PDF",
                            isBestMatch = index == 0 && searchQuery.length > 2,
                            iconGradient = gradient,
                            onClick = { onNavigateToPlayer(result.id) }
                        )
                    }
                }
            }

            if (showFilters) {
                ModalBottomSheet(
                    onDismissRequest = { showFilters = false },
                    sheetState = sheetState,
                    containerColor = Color(0xFF1A1C20),
                    scrimColor = Color.Black.copy(alpha = 0.6f),
                    dragHandle = { BottomSheetDefaults.DragHandle(color = Color.White.copy(alpha = 0.2f)) }
                ) {
                    FilterSheetContent(
                        currentSort = sortBy,
                        currentFilter = progressFilter,
                        onSortChange = viewModel::onSortByChange,
                        onFilterChange = viewModel::onProgressFilterChange,
                        onClose = { showFilters = false }
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterSheetContent(
    currentSort: SortBy,
    currentFilter: SearchProgressFilter,
    onSortChange: (SortBy) -> Unit,
    onFilterChange: (SearchProgressFilter) -> Unit,
    onClose: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .navigationBarsPadding()
    ) {
        Text(
            text = "Filtros Avançados",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Ordenar por",
            color = colorScheme.outline,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SortChip(label = "Recentes", selected = currentSort == SortBy.NEWEST) {
                onSortChange(
                    SortBy.NEWEST
                )
            }
            SortChip(
                label = "Título",
                selected = currentSort == SortBy.TITLE
            ) { onSortChange(SortBy.TITLE) }
            SortChip(label = "Progresso", selected = currentSort == SortBy.PROGRESS) {
                onSortChange(
                    SortBy.PROGRESS
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Status de leitura",
            color = colorScheme.outline,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterOption(
                label = "Todos os livros",
                selected = currentFilter == SearchProgressFilter.ALL,
                onClick = { onFilterChange(SearchProgressFilter.ALL) }
            )
            FilterOption(
                label = "Em progresso",
                selected = currentFilter == SearchProgressFilter.IN_PROGRESS,
                onClick = { onFilterChange(SearchProgressFilter.IN_PROGRESS) }
            )
            FilterOption(
                label = "Concluídos",
                selected = currentFilter == SearchProgressFilter.COMPLETED,
                onClick = { onFilterChange(SearchProgressFilter.COMPLETED) }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onClose,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
        ) {
            Text("Aplicar Filtros", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun SortChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = if (selected) colorScheme.primary.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.05f),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (selected) colorScheme.primary.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.1f)
        )
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (selected) colorScheme.primary else colorScheme.outline,
            fontSize = 13.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun FilterOption(label: String, selected: Boolean, onClick: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (selected) Color.White.copy(alpha = 0.05f) else Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = if (selected) Color.White else colorScheme.outline,
                fontSize = 15.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
            if (selected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchScreenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        SearchScreen()
    }
}
