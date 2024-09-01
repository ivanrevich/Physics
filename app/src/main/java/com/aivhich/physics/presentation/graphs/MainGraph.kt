package com.aivhich.physics.presentation.graphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aivhich.physics.presentation.screens.HomeScreen
import com.aivhich.physics.presentation.screens.LessonScreen
import com.aivhich.physics.presentation.screens.ProfileScreen
import com.aivhich.zaryaui.BodyText

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { it ->
        Column(
            Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
        ) {
            HomeNavGraph(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier.shadow(
                15.dp,
                RectangleShape,
                clip = true,
                ambientColor = MaterialTheme.colorScheme.onBackground,
                spotColor = MaterialTheme.colorScheme.onBackground
            ),
            tonalElevation = 0.dp,
        ) {
            //Spacer(Modifier.width(5.dp))
            screens.forEach { item ->
                val selected = item.route == currentDestination?.route

                NavigationBarItem(
                    colors =  NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    alwaysShowLabel = false,
                    selected = selected,
                    onClick = { navController.navigate(item.route) },
                    label = {
                        BodyText(
                            text = item.title,
                            weight = FontWeight.SemiBold,
                        )
                    },
                    icon = {
                        androidx.compose.material3.Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "Icon"
                        )
                    }
                )
            }

            //Spacer(Modifier.width(5.dp))
        }
    }
}


@Composable
fun HomeNavGraph(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home.route,
        BottomBarScreen.Profile.route
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route,
        enterTransition = {
            slideIntoContainer(
                if(screens.indexOf(currentDestination?.route)>screens.indexOf(navController.previousBackStackEntry?.destination?.route)) AnimatedContentTransitionScope.SlideDirection.Left else AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                if(screens.indexOf(currentDestination?.route)>screens.indexOf(navController.previousBackStackEntry?.destination?.route)) AnimatedContentTransitionScope.SlideDirection.Left else AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        detailedViewGraph(navController)
    }
}


fun NavGraphBuilder.detailedViewGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailedScreen.Lesson.route
    ) {
        composable(
            route = DetailedScreen.Lesson.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let { LessonScreen(it) }
        }
    }
}

sealed class DetailedScreen(val route: String) {
    object Lesson : DetailedScreen(route = "lesson/{id}")
}