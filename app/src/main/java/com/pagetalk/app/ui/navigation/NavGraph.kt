package com.pagetalk.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pagetalk.app.ui.presentation.home.HomeScreen
import com.pagetalk.app.ui.presentation.importpdf.ImportPdfScreen
import com.pagetalk.app.ui.presentation.search.SearchScreen
import com.pagetalk.app.ui.presentation.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash
    ) {
        composable<Screen.Splash> {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Home) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.Home> {
            HomeScreen(
                onNavigateToImport = {
                    navController.navigate(Screen.ImportPdf)
                },
                onNavigateToSearch = {
                    navController.navigate(Screen.Search) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Screen.ImportPdf> {
            ImportPdfScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screen.Search> {
            SearchScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home) {
                        popUpTo(Screen.Home) { inclusive = true }
                    }
                }
            )
        }
    }
}
