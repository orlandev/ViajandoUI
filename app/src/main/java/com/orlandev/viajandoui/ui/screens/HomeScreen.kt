package com.orlandev.viajandoui.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme
import com.orlandev.viajandoui.utils.LinePlaceholderShimmer

@Composable
fun HomeScreen(navController: NavController) {

    val listOfNews = listOf<ViajandoNewsType>(
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
            schedule = "12:00"
        ),
        ViajandoNewsType.Event(
            "Evento 2",
            "Descripcion 3",
            "description",
            "https://source.unsplash.com/random/350×300",
            schedule = "12:00"
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
            "Descripcion 5",
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
        is ViajandoNewsType.Alert -> TODO()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNews(news: ViajandoNewsType.News, onClick: () -> Unit = {}) {


    val loadingImage = remember {
        mutableStateOf(false)
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClick()
            },
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .placeholder(
                    visible = loadingImage.value,
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                            alpha = 0.6f
                        ),
                    ),
                ),

            onLoading = {
                loadingImage.value = true
            },
            onSuccess = {
                loadingImage.value = false
            },
            contentScale = ContentScale.Crop,
            model = news.imageUrl, contentDescription = null
        )
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier) {
                if (loadingImage.value) {
                    LinePlaceholderShimmer(80.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    LinePlaceholderShimmer()

                } else {
                    Text(
                        text = news.title, style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = news.subTitle, style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), contentAlignment = Alignment.CenterEnd
            ) {
                Icon(imageVector = Icons.Default.Newspaper, contentDescription = null)
            }
        }
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

    //Alertas enviadas por la compania viajando
    class Alert(title: String, description: String, val imageUrl: String) :
        ViajandoNewsType(title = title, subTitle = "", description = description)

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