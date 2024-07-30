package com.aivhich.physics.presentation.graphs

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    //mainNavHostController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController)
        composable(route = Graph.QUESTIONS) {
        }
        composable(route = Graph.HOME) {
            MainScreen()
            BackHandler(true) {}
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val QUESTIONS = "question_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}