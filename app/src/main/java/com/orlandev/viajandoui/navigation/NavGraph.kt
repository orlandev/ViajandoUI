package com.orlandev.viajandoui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavGraph(navController: NavController) {

    val itemsBottomBarItems = listOf(
        NavRouter.HomeScreenRoute,
        NavRouter.BookingScreenRoute,
        NavRouter.AgenciesScreenRoute,
        NavRouter.AboutScreenRoute,
        NavRouter.ProfileScreenRoute,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination







}