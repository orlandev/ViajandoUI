package com.orlandev.viajandoui.ui.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    fun haveAlert(): Boolean {
        //Simula una alerta
        return Random.nextInt(1,3)==2
    }

    val listOfNews = listOf<ViajandoNewsType>(
        ViajandoNewsType.Alert(
            "Alerta Viajando",
            "Estimados usuarios de viajando, en esta época la empresa Viajero se encuentra vendiendo pasajes de temporada de verano por lo que se están experimentando pico de solicitudes a las 8:30 am lo que ocasiona congestión en la red.\n" +
                    "La explicación técnica de lo que ocurre cuando a las 8:30 am, cuando los usuarios intentan seleccionar asientos disponibles y se queda cargando o no pueden seleccionarlos es:\n" +
                    "A las 8:30 am se realizan un número exponencialmente superior de  conexiones con solicitudes de compra a los servidores para una disponibilidad de pasajes por la apk muy inferior, lo que ocasiona una congestión temporal en los servidores.\n" +
                    "Las solicitudes que primero lleguen a los servidores de dispositivos que cuenten con la conexión más rápida y estable, son las que tendrán mayores posibilidades de comprar.\n" +
                    "Tener una conexión rápida y estable no significa solo contar con buena cobertura.",
            ""
        ),
        ViajandoNewsType.News(
            "Titulo 1",
            "Descripcion 1",
            "description",
            "https://source.unsplash.com/random/300×300"
        ),

        ViajandoNewsType.Event(
            "Evento 1",
            "Descripcion 2",
            "description",
            "https://source.unsplash.com/random/300×200",
            schedule = "12:00 PM - 4:00 PM"
        ),
        ViajandoNewsType.Event(
            "Evento 2",
            "Descripcion 3",
            "description",
            "https://source.unsplash.com/random/350×300",
            schedule = "8:00 AM - 2:00 PM"
        ),

        ViajandoNewsType.News(
            "Titulo 2",
            "Descripcion 2",
            "description",
            "https://source.unsplash.com/random/320×300"
        ),
        ViajandoNewsType.News(
            "Titulo 3",
            "Descripcion 3",
            "description",
            "https://source.unsplash.com/random/300×310"
        ),

        ViajandoNewsType.Offer(
            "Oferta 1",
            "Descripcion 4",
            "https://source.unsplash.com/random/200×200",
            34.99
        ),
        ViajandoNewsType.Event(
            "Evento 3",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "description",
            "https://source.unsplash.com/random/300×250",
            schedule = "12:00"
        ),
        ViajandoNewsType.Event(
            "Evento 4",
            "Descripcion 6",
            "description",
            "https://source.unsplash.com/random/300×315",
            schedule = "12:00"
        ),
        ViajandoNewsType.Offer(
            "Oferta 2",
            "Descripcion 7",
            "https://source.unsplash.com/random/300×325",
            10.99
        ),

        )
}