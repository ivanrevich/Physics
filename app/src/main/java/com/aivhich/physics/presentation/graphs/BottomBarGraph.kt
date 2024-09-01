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
        icon = R.drawable.baseline_home_24,
        activeIcon = R.drawable.baseline_home_24
    )
    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "Я",
        icon = R.drawable.baseline_account_circle_24,
        activeIcon = R.drawable.baseline_account_circle_24
    )
}
