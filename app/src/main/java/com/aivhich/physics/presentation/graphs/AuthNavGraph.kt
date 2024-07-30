package com.aivhich.physics.presentation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreens.SetNativeLang.route,
    ) {
        composable(route = AuthScreens.SetNativeLang.route) {
        }
        composable(route = AuthScreens.Auth.route) {
        }
    }
}

sealed class AuthScreens(val route: String) {
    object SetNativeLang : AuthScreens(route = "SET_NATIVE_LANG")
    object Auth : AuthScreens(route = "AUTH")
}

