package com.orlandev.viajandoui.utils

import com.orlandev.viajandoui.ui.screens.home.listOfRoutes
import kotlin.random.Random


fun Random.randomTime(): String {
    val hour = Random.nextInt(0, 12)
    val minute = Random.nextInt(0, 60)
    val M = if (Random.nextInt(0, 1) == 0) "AM" else "PM"
    val hourString = if (hour < 10) "0$hour" else hour.toString()
    val minureString = if (minute < 10) "0$minute" else minute.toString()
    return "$hourString:$minureString$M"
}

fun Random.randomCities(): String {
    val city1 = Random.nextInt(0, listOfRoutes.size)
    return listOfRoutes[city1]
}

fun Random.randomPrice(): String {
    val price = Random.nextInt(0, 100)
    return "$$price.00"
}

fun Random.randomChairs(): String {
    val chairs = Random.nextInt(1, 100)
    return "$chairs"
}

