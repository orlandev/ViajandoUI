package com.orlandev.viajandoui.model

data class Passage(
    val origen: String,
    val destiny: String,
    val transportType: TravelTransportType,
    val price: Int,
    val cantSeatsAvailable: Int,
    val startTime: String,
    val arriveTime: String,
)
