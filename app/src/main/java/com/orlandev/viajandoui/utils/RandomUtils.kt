package com.orlandev.viajandoui.utils

import com.orlandev.viajandoui.model.Passage
import com.orlandev.viajandoui.model.TravelTransportType
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

fun Random.randomPassage(): Passage {
    return Passage(
        origen = Random.randomCities(),
        destiny = Random.randomCities(),
        price = Random.randomPrice(),
        cantSeatsAvailable = Random.randomChairs(),
        transportType = Random.randomTransportType(),
        startTime = Random.randomTime(),
        arriveTime = Random.randomTime(),
    )
}

fun Random.randomTransportType(): TravelTransportType {
    return when (Random.nextInt(0, 4)) {
        0 -> TravelTransportType.Bus
        1 -> TravelTransportType.Train
        2 -> TravelTransportType.Boat
        3 -> TravelTransportType.Airplane
        else -> TravelTransportType.Bus
    }
}

fun Random.randomCities(): String {
    val city1 = Random.nextInt(0, listOfRoutes.size - 1)
    return listOfRoutes[city1]
}

fun Random.randomPrice(): Int {
    return Random.nextInt(10, 100)
}

fun Random.randomChairs(): Int {
    return Random.nextInt(1, 100)
}

