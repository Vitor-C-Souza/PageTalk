package com.pagetalk.app.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object Splash : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data object ImportPdf : Screen()

    @Serializable
    data object Search : Screen()

    @Serializable
    data object Library : Screen()

    @Serializable
    data object Profile : Screen()

    @Serializable
    data class Player(val bookId: Long) : Screen()
}
