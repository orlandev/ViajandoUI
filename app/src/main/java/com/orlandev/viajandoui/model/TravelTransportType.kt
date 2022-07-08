package com.orlandev.viajandoui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orlandev.viajandoui.R

sealed class TravelTransportType(@DrawableRes val icon: Int, @StringRes val name: Int) {
    object Bus : TravelTransportType(R.drawable.ic_bus, R.string.bus_name)
    object Airplane : TravelTransportType(R.drawable.ic_airplane, R.string.airplane_name)
    object Boat : TravelTransportType(R.drawable.ic_boat, R.string.boat_name)
    object Train : TravelTransportType(R.drawable.ic_train, R.string.train_name)
}

