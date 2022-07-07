package com.orlandev.viajandoui.navigation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.ui.screens.about.AboutScreen
import com.orlandev.viajandoui.ui.screens.agencies.AgenciesScreen
import com.orlandev.viajandoui.ui.screens.booking.BookingScreen
import com.orlandev.viajandoui.ui.screens.faq.FaqScreen
import com.orlandev.viajandoui.ui.screens.home.HomeScreen
import com.orlandev.viajandoui.ui.screens.profile.ProfileScreen
import com.orlandev.viajandoui.ui.screens.splash_screen.SplashScreen
import com.orlandev.viajandoui.utils.ShareIntent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    val appName = stringResource(id = R.string.app_name)

    var appBarTitle by remember {
        mutableStateOf(appName)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var expandeFabButtonState by remember {
        mutableStateOf(true)
    }
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()

    val state = TopAppBarScrollState(0f, 0f, 0f)

    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            state = state,
            decayAnimationSpec = decayAnimationSpec
        )
    }

    val context = LocalContext.current
    val sitransAppLink = stringResource(id = R.string.sitrans_viajando_apklis_link)


    val showingSplashScreen = rememberSaveable {
        mutableStateOf(true)
    }


    LaunchedEffect(Unit)
    {
        delay(2000)
        showingSplashScreen.value = false
    }

    val scope = rememberCoroutineScope()

    if (showingSplashScreen.value) {
        AnimatedVisibility(visible = showingSplashScreen.value) {
            SplashScreen()
        }
    } else {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MediumTopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = {
                        Text(text = appBarTitle)
                    },
                    actions = {
                        IconButton(onClick = {

                            navController.navigate(NavRouter.FaqScreenRoute.route)

                        }) {
                            Icon(Icons.Outlined.HelpOutline, contentDescription = null)
                        }

                        IconButton(onClick = {
                            ShareIntent.shareIt(
                                ctx = context,
                                shareBy = appName,
                                shareText = sitransAppLink
                            )
                        }) {
                            Icon(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                imageVector = Icons.Outlined.Share,
                                contentDescription = stringResource(
                                    id = R.string.share_text_app_bar_description
                                )
                            )
                        }
                    },
                    navigationIcon = {

                        val isMainPages =
                            itemsBottomBarItems.firstOrNull { it.route == currentDestination?.route }

                        if (isMainPages != null) {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        Toast.makeText(
                                            context,
                                            "Abirir drawer -> En Desarrollo",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.Outlined.Menu,
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    contentDescription = stringResource(id = R.string.menu_navicon_content_description)
                                )
                            }
                        } else {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                }
                            ) {
                                Icon(
                                    Icons.Outlined.ArrowBack,
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    contentDescription = stringResource(id = R.string.menu_navicon_content_description)
                                )
                            }
                        }
                    }
                )
            },
            bottomBar =
            {
                //TODO ( ADD Show/Hide Scroll Behavior)
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
                    route = NavRouter.SplashScreen.route,
                ) {
                    SplashScreen(navController = navController)
                }

                composable(
                    route = NavRouter.FaqScreenRoute.route,
                ) {
                    //appBarTitle = stringResource(id = )
                    FaqScreen()
                }

                composable(
                    route = NavRouter.HomeScreenRoute.route,
                ) {
                    appBarTitle = appName
                    HomeScreen(navController = navController)
                }


                composable(
                    route = NavRouter.AgenciesScreenRoute.route,
                ) {
                    appBarTitle = NavRouter.AgenciesScreenRoute.resourceStringId?.let {
                        stringResource(it)
                    } ?: appName
                    AgenciesScreen(navController = navController)
                }


                composable(
                    route = NavRouter.BookingScreenRoute.route,
                ) {
                    appBarTitle = NavRouter.BookingScreenRoute.resourceStringId?.let {
                        stringResource(it)
                    } ?: appName
                    BookingScreen(navController = navController)
                }

                composable(
                    route = NavRouter.ProfileScreenRoute.route,
                ) {
                    appBarTitle = NavRouter.ProfileScreenRoute.resourceStringId?.let {
                        stringResource(it)
                    } ?: appName
                    ProfileScreen(navController = navController)
                }

                composable(
                    route = NavRouter.AboutScreenRoute.route,
                ) {
                    appBarTitle = NavRouter.AboutScreenRoute.resourceStringId?.let {
                        stringResource(it)
                    } ?: appName
                    AboutScreen()
                }

            }
        }
    }

}



