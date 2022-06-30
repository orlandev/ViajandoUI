package com.orlandev.viajandoui.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.orlandev.viajandoui.ui.screens.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(navController: NavHostController) {

    val itemsBottomBarItems = listOf(
        NavRouter.AgenciesScreenRoute,
        NavRouter.BookingScreenRoute,
        NavRouter.HomeScreenRoute,
        NavRouter.ProfileScreenRoute,
        NavRouter.AboutScreenRoute,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar =
        {
            NavigationBar {
                itemsBottomBarItems.forEach { screen ->
                    NavigationBarItem(
                        modifier = Modifier.fillMaxHeight(),
                        alwaysShowLabel = false,
                        icon = {
                            screen.resourceDrawableId?.let {
                                Icon(
                                    painterResource(id = it),
                                    contentDescription =
                                    if (screen.resourceStringId != null)
                                        stringResource(
                                            screen.resourceStringId
                                        ) else ""
                                )
                            }
                        },
                        label = {
                            screen.resourceStringId?.let {
                                Text(stringResource(it))
                            }
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                //popUpTo(navController.graph.findStartDestination().id) {
                                //     saveState = true
                                //}
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navController = navController,
            startDestination = NavRouter.HomeScreenRoute.route
        )
        {

            composable(
                route = NavRouter.HomeScreenRoute.route,
            ) {
                HomeScreen(navController = navController)
            }


            composable(
                route = NavRouter.AgenciesScreenRoute.route,
            ) {
                AgenciesScreen(navController = navController)
            }


            composable(
                route = NavRouter.BookingScreenRoute.route,
            ) {
                BookingScreen(navController = navController)
            }

            composable(
                route = NavRouter.ProfileScreenRoute.route,
            ) {
                ProfileScreen(navController = navController)
            }

            composable(
                route = NavRouter.AboutScreenRoute.route,
            ) {
                AboutScreen(navController = navController)
            }

        }
    }
}



