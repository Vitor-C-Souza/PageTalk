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
import com.pagetalk.app.ui.theme.PageTalkTheme

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Search : BottomNavItem("search", "Buscar", Icons.Default.Search)
    object Library : BottomNavItem("library", "Biblioteca", Icons.AutoMirrored.Filled.LibraryBooks)
    object Profile : BottomNavItem("profile", "Perfil", Icons.Default.Person)
}

@Composable
fun PageTalkBottomNavigation(
    currentRoute: String?,
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
            val isSelected = currentRoute == item.route

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
                    unselectedIconColor = colorScheme.outline, // #7B8190 mapping
                    unselectedTextColor = colorScheme.outline,
                    indicatorColor = Color.Transparent // Removing default indicator pill
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

                PageTalkBottomNavigation(
                    currentRoute = "home",
                    onNavItemClick = {}
                )
            }
        }
    }
}
