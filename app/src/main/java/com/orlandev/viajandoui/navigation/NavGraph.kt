package com.orlandev.viajandoui.navigation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.orlandev.viajandoui.R
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


    var extendedButton by remember {
        mutableStateOf(false)
    }

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()

    val state = TopAppBarScrollState(0f, 0f, 0f)

    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec, state = state)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    Icon(
                        Icons.Outlined.Menu,
                        contentDescription = stringResource(id = R.string.menu_navicon_content_description)
                    )
                }
            )
        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),

                onClick = {
                    extendedButton = !extendedButton
                }) {
                Icon(Icons.Default.Bookmark, contentDescription = null)
                if (scrollBehavior.state.contentOffset < 0) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(id = R.string.reserv))
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
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



