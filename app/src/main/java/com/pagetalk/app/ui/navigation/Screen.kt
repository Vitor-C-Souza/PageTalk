package com.pagetalk.app.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    object Splash : Screen()

    @Serializable
    object Home : Screen()
}