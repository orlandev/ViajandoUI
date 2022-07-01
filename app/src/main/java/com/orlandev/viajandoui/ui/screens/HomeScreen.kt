package com.orlandev.viajandoui.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme

@Composable
fun HomeScreen(navController: NavController) {

    val listOfNews = listOf<ViajandoNewsType>(
        ViajandoNewsType.News(
            "Titulo 1",
            "Descripcion 1",
            "description",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png"
        ),

        ViajandoNewsType.Event(
            "Evento 1",
            "Descripcion 2",
            "description",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png",
            schedule = "12:00"
        ),
        ViajandoNewsType.Event(
            "Evento 2",
            "Descripcion 3",
            "description",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png",
            schedule = "12:00"
        ),

        ViajandoNewsType.News(
            "Titulo 2",
            "Descripcion 2",
            "description",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png"
        ),
        ViajandoNewsType.News(
            "Titulo 3",
            "Descripcion 3",
            "description",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png"
        ),

        ViajandoNewsType.Offer(
            "Oferta 1",
            "Descripcion 4",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png",
            34.99
        ),
        ViajandoNewsType.Event(
            "Evento 3",
            "Descripcion 5",
            "description",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png",
            schedule = "12:00"
        ),
        ViajandoNewsType.Event(
            "Evento 4",
            "Descripcion 6",
            "description",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png",
            schedule = "12:00"
        ),
        ViajandoNewsType.Offer(
            "Oferta 2",
            "Descripcion 7",
            "https://viajando.com/wp-content/uploads/2020/01/viajando-logo-1.png",
            10.99
        ),

        )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(listOfNews) { news ->
            NewsItem(news = news)
        }
    }
}

@Composable
fun NewsItem(news: ViajandoNewsType) {
    when (news) {
        is ViajandoNewsType.News -> {

            CardNews(news)

        }
        is ViajandoNewsType.Event -> {
            Text(text = news.title)
        }
        is ViajandoNewsType.Offer -> {
            Text(text = news.title)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNews(news: ViajandoNewsType.News) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(text = news.title)
        Text(text = news.subTitle)
        Text(text = news.description)
    }
}


@Preview(showBackground = true)
@Composable
fun CardNewsPreview() {
    ViajandoUITheme {
        CardNews(ViajandoNewsType.News("Title", "Description", "Image", "Link"))
    }
}


sealed class ViajandoNewsType(val title: String, val subTitle: String, val description: String) {
    class News(title: String, subTitle: String, description: String, val imageUrl: String) :
        ViajandoNewsType(title = title, subTitle = subTitle, description = description)

    class Event(
        title: String,
        subTitle: String,
        description: String,
        val imageUrl: String,
        val schedule: String
    ) :
        ViajandoNewsType(title = title, subTitle = subTitle, description = description)

    class Offer(title: String, description: String, val imageUrl: String, val price: Double) :
        ViajandoNewsType(title = title, subTitle = "", description = description)
}