package com.orlandev.viajandoui.model

data class SearchTravelModel(
    val origin: String,
    val destiny: String,
    val travelType: TravelType,
    val dateIda: String,
    val dateIdaYRegreso: String?,
)