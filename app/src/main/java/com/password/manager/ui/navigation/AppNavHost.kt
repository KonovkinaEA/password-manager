package com.password.manager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.password.manager.ui.screens.account.AccountScreen
import com.password.manager.ui.screens.list.ListScreen
import com.password.manager.ui.screens.start.StartScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AppNavHost(navController: NavHostController, imageLoader: ImageLoader) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Start.route
    ) {
        composable(Start.route) {
            StartScreen(toNextScreen = { navController.navigate(List.route) })
        }
        composable(List.route) {
            ListScreen(
                addAccount = { navController.navigate(Account.route) },
                editAccount = navController::navigateToAccount,
                clearCache = {
                    imageLoader.diskCache?.clear()
                    imageLoader.memoryCache?.clear()
                }
            )
        }
        composable(Account.route) {
            AccountScreen(onScreenClose = {
                navController.navigate(List.route) { popUpTo(List.route) { inclusive = true } }
            })
        }
        composable(Account.routeWithArgs, arguments = Account.arguments) {
            AccountScreen(onScreenClose = {
                navController.navigate(List.route) { popUpTo(List.route) { inclusive = true } }
            })
        }
    }
}

private fun NavController.navigateToAccount(id: String = "") {
    this.navigate(Account.navToOrderWithArgs(id))
}
