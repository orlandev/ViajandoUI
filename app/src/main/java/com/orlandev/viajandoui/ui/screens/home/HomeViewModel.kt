package com.orlandev.viajandoui.ui.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val listOfNews = listOf<ViajandoNewsType>(
        ViajandoNewsType.Alert(
            "Titulo 1",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Sed euismod, urna eu tincidunt consectetur, nisi nisl " +
                    "consequat nisl, eget consectetur nisl nisl euismod " +
                    "nisl. Sed euismod, urna eu tincidunt consectetur, " +
                    "nisi nisl consectetur nisl, eget consectetur nisl " +
                    "nisl euismod nisl. Sed euismod, urna eu tincidunt " +
                    "consectetur, nisi nisl consectetur nisl, eget " +
                    "consectetur nisl nisl euismod nisl. Sed euismod, " +
                    "urna eu tincidunt consectetur, nisi nisl consectetur " +
                    "nisl, eget consectetur nisl nisl euismod nisl. Sed " +
                    "euismod, urna eu tincidunt consectetur, nisi nisl " +
                    "consectetur nisl, eget consectetur nisl nisl euismod " +
                    "nisl. Sed euismod, urna eu tincidunt consectetur, " +
                    "nisi nisl consectetur nisl, eget consectetur nisl " +
                    "nisl euismod nisl. Sed euismod, urna eu tincidunt " +
                    "consectetur, nisi nisl consectetur nisl, eget " +
                    "consectetur nisl nisl euismod nisl. Sed euismod, " +
                    "urna eu tincidunt consectetur, nisi nisl consectetur " +
                    "nisl, eget consectetur nisl nisl euismod nisl. Sed " +
                    "euismod,",

            "https://source.unsplash.com/random/400×300"
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