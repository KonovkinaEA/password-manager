package com.password.manager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.password.manager.ui.screens.list.ListScreen
import com.password.manager.ui.screens.start.StartScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Start.route
    ) {
        composable(Start.route) {
            StartScreen(toNextScreen = { navController.navigate(List.route) })
        }
        composable(List.route) {
            ListScreen(addAccount = { /*TODO*/ }, editAccount = { /*TODO*/ })
        }
    }
}
