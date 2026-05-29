package com.pagetalk.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pagetalk.app.ui.navigation.Screen
import com.pagetalk.app.ui.theme.PageTalkTheme

sealed class BottomNavItem(
    val screen: Screen,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(Screen.Home, "Home", Icons.Default.Home)
    object Search : BottomNavItem(Screen.Search, "Buscar", Icons.Default.Search)
    object Library : BottomNavItem(
        Screen.Home,
        "Biblioteca",
        Icons.AutoMirrored.Filled.LibraryBooks
    ) // Placeholder

    object Profile : BottomNavItem(Screen.Home, "Perfil", Icons.Default.Person) // Placeholder
}

@Composable
fun BottomNavigation(
    currentScreen: Screen?,
    onNavItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Library,
        BottomNavItem.Profile
    )

    val colorScheme = MaterialTheme.colorScheme

    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = colorScheme.surface,
        contentColor = colorScheme.onSurface,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            // Simple comparison for data objects
            val isSelected = currentScreen == item.screen

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavItemClick(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorScheme.primary,
                    selectedTextColor = colorScheme.primary,
                    unselectedIconColor = colorScheme.outline,
                    unselectedTextColor = colorScheme.outline,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1115)
@Composable
fun BottomNavigationPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(24.dp)
        ) {
            Column {
                Text(
                    text = "Bottom Navigation",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                BottomNavigation(
                    currentScreen = Screen.Home,
                    onNavItemClick = {}
                )
            }
        }
    }
}
