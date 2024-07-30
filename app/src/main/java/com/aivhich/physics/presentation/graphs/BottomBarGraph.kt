package com.aivhich.physics.presentation.graphs

import com.aivhich.physics.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val activeIcon: Int
) {

    object Home : BottomBarScreen(
        route = "HOME",
        title = "Дом",
        icon = R.drawable.ic_launcher_background,
        activeIcon = R.drawable.ic_launcher_background
    )
    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "Я",
        icon = R.drawable.ic_launcher_background,
        activeIcon = R.drawable.ic_launcher_background
    )
}
