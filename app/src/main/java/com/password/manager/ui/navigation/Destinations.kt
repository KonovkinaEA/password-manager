package com.password.manager.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object Start : Destination {
    override val route = "start"
}

object List : Destination {
    override val route = "list"
}

object Account : Destination {
    const val id = "id"
    override val route = "account"

    const val routeWithArgs = "account/{id}"

    val arguments = listOf(
        navArgument(id) {
            type = NavType.StringType
        }
    )

    fun navToOrderWithArgs(id: String = "") = "$route/$id"
}
