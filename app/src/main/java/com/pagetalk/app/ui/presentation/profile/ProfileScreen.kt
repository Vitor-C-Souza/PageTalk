package com.pagetalk.app.ui.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.pagetalk.app.ui.navigation.Screen
import com.pagetalk.app.ui.presentation.profile.components.ProfileInfoCard
import com.pagetalk.app.ui.presentation.profile.components.SettingItem
import com.pagetalk.app.ui.presentation.profile.components.StatCard
import com.pagetalk.app.ui.theme.PageTalkTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToLibrary: () -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colorScheme.background,
        topBar = {
            HeaderCustom(
                title = "Perfil",
                modifier = Modifier.padding(end = 16.dp),
                actions = {
                    Surface(
                        onClick = { /* TODO: Settings */ },
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.05f),
                        border = BorderStroke(
                            1.dp,
                            Color.White.copy(alpha = 0.1f)
                        )
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                Icons.Default.Settings,
                                null,
                                tint = colorScheme.tertiary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(
                currentScreen = Screen.Profile,
                onNavItemClick = { item ->
                    when (item) {
                        BottomNavItem.Home -> onNavigateToHome()
                        BottomNavItem.Search -> onNavigateToSearch()
                        BottomNavItem.Library -> onNavigateToLibrary()
                        BottomNavItem.Profile -> { /* Already here */
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
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // User Card
            item {
                ProfileInfoCard()
            }

            // Statistics
            item {
                Column {
                    Text(
                        text = "Estatísticas",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        StatCard(
                            label = "Tempo ouvindo",
                            value = "124h",
                            icon = Icons.Default.Headphones,
                            color = colorScheme.primary,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        StatCard(
                            label = "Dias seguidos",
                            value = "15",
                            icon = Icons.Default.Adjust,
                            color = Color(0xFFFACC15),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        StatCard(
                            label = "Velocidade média",
                            value = "1.5x",
                            icon = Icons.AutoMirrored.Filled.VolumeUp,
                            color = colorScheme.secondary,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        StatCard(
                            label = "Conquistas",
                            value = "12",
                            icon = Icons.Default.EmojiEvents,
                            color = com.pagetalk.app.ui.theme.Success,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Progress Month
            item {
                Column {
                    Text(
                        text = "Progresso este mês",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        color = colorScheme.surfaceVariant.copy(alpha = 0.3f),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Meta: 40 horas", color = colorScheme.onSurfaceVariant)
                                Text(
                                    "32h / 40h",
                                    color = colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(12.dp)
                                    .background(colorScheme.background, CircleShape)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .fillMaxHeight()
                                        .background(
                                            Brush.horizontalGradient(
                                                listOf(
                                                    colorScheme.primary,
                                                    colorScheme.secondary
                                                )
                                            ),
                                            CircleShape
                                        )
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "80% completo • Faltam 8 horas",
                                color = colorScheme.outline,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Preferences
            item {
                Column {
                    Text(
                        text = "Preferências",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        SettingItem(
                            label = "Áudio",
                            subtitle = "Qualidade e velocidade",
                            icon = Icons.AutoMirrored.Filled.VolumeUp,
                            iconColor = colorScheme.primary
                        )
                        SettingItem(
                            label = "Notificações",
                            subtitle = "Lembretes e alertas",
                            icon = Icons.Default.Notifications,
                            iconColor = Color(0xFFFACC15)
                        )
                        SettingItem(
                            label = "Aparência",
                            subtitle = "Tema dark ativo",
                            icon = Icons.Default.DarkMode,
                            iconColor = colorScheme.secondary
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPreview() {
    PageTalkTheme(darkTheme = true, dynamicColor = false) {
        ProfileScreen()
    }
}
