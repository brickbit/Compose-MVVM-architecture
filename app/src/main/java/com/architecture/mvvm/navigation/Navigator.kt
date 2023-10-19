package com.architecture.mvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.architecture.mvvm.screens.detail.CourseDetailRoute
import com.architecture.mvvm.screens.list.ListCourseRoute

@Composable
fun Navigator() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.List.name
    ) {
        composable(Routes.List.name) {
           ListCourseRoute(navController)
        }
        composable(
            route = "${Routes.Detail.name}/{link}",
            arguments = listOf(navArgument("link") { type = NavType.StringType })
        ) {backStackEntry ->
            CourseDetailRoute(link = backStackEntry.arguments?.getString("link") ?: "")
        }
    }
}