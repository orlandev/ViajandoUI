package com.orlandev.viajandoui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orlandev.viajandoui.R

sealed class NavRouter(
    val route: String,
    @DrawableRes val resourceDrawableId: Int? = null,
    @StringRes val resourceStringId: Int? = null,
    argument: String? = null
) {

    //BottomBar sections
    object HomeScreenRoute :
        NavRouter(
            route = "home",
            resourceDrawableId = R.drawable.ic_outline_home_24,
            resourceStringId = R.string.home
        )

    object PerfilScreenRoute : NavRouter(
        route = "profile",
        resourceDrawableId = R.drawable.profile_24,
        resourceStringId = R.string.profile
    )

    object ReservationsScreenRoute : NavRouter(
        route = "reservations",
        resourceDrawableId = R.drawable.reservations,
        resourceStringId = R.string.reservations
    )

    object AboutScreenRoute : NavRouter(
        route = "reservations",
        resourceDrawableId = R.drawable.about,
        resourceStringId = R.string.about
    )

    object AgenciesScreenRoute : NavRouter(
        route = "agencies",
    )
}