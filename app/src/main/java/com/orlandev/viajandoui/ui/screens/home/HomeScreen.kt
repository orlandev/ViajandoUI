package com.orlandev.viajandoui.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme
import com.orlandev.viajandoui.utils.LinePlaceholderShimmer
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val generalItemPadding = 16.dp

    val listOfNews = homeViewModel.listOfNews


    val alert = listOfNews.filterIsInstance<ViajandoNewsType.Alert>().firstOrNull()
    LazyColumn(modifier = Modifier.fillMaxSize()) {

        if (alert != null) {
            item {
                Text(
                    modifier = Modifier.padding(horizontal = generalItemPadding),
                    text = stringResource(id = R.string.alert_sitrans_text),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            item {

                CardAlert(news = alert)

            }
        }

        item {
            Text(text = "Noticias")
        }

        item {
            LazyRow {
                items(listOfNews.filterIsInstance<ViajandoNewsType.News>()) { news ->
                    CardNews(news)
                }
            }
        }

        item {
            Text(text = "Eventos")
        }
        items(listOfNews.filterIsInstance<ViajandoNewsType.Event>()) { event ->
            CardEvents(event)
        }

        item {
            Text(text = "Ventas")
        }
        items(listOfNews.filterIsInstance<ViajandoNewsType.Offer>()) { offer ->
            CardOffers(offer)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAlert(news: ViajandoNewsType.Alert, onClick: () -> Unit = {}) {
    val loadingImage = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        //Simulando peticion a la base de datos
        delay(2000)
        loadingImage.value = false
    }


    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
            .padding(16.dp)
            .clickable {
                onClick()
            },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
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

                contentScale = ContentScale.FillBounds,
                model = news.imageUrl.ifEmpty { R.drawable.alerta_viajando },
                contentDescription = null
            )
            Column(modifier = Modifier.fillMaxHeight().padding(8.dp), verticalArrangement = Arrangement.SpaceAround) {
                if (loadingImage.value) {
                    val height=12.dp
                    LinePlaceholderShimmer(80.dp,height)
                    Spacer(modifier = Modifier.height(8.dp))
                    LinePlaceholderShimmer(minHeight = height)
                    LinePlaceholderShimmer(minHeight = height)
                    LinePlaceholderShimmer(minHeight = height)
                    Spacer(modifier = Modifier.height(8.dp))
                    LinePlaceholderShimmer(80.dp,height)
                } else {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        text = news.title,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        text = news.description, style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = {
                        onClick()
                    }) {
                        Text(text = stringResource(id = R.string.see_more))
                    }

                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEvents(news: ViajandoNewsType.Event, onClick: () -> Unit = {}) {

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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
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


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(8f),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    if (loadingImage.value) {
                        LinePlaceholderShimmer(80.dp)
                        Spacer(modifier = Modifier.height(8.dp))
                        LinePlaceholderShimmer(130.dp)

                    } else {

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = news.title,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = news.subTitle, style = MaterialTheme.typography.titleSmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Text(
                            text = news.schedule, style = MaterialTheme.typography.bodySmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )

                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 8.dp), contentAlignment = Alignment.BottomEnd
                ) {
                    Icon(imageVector = Icons.Default.Event, contentDescription = null)
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardOffers(news: ViajandoNewsType.Offer, onClick: () -> Unit = {}) {

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

        Row(modifier = Modifier) {
            AsyncImage(
                modifier = Modifier
                    .size(130.dp)
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
            Row(
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
                    .padding(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(8f),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    if (loadingImage.value) {
                        LinePlaceholderShimmer(80.dp)
                        Spacer(modifier = Modifier.height(8.dp))
                        LinePlaceholderShimmer(130.dp)

                    } else {

                        Text(
                            text = news.title,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Text(
                            text = news.subTitle, style = MaterialTheme.typography.titleSmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 8.dp), contentAlignment = Alignment.BottomEnd
                ) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                }
            }
        }
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
            .fillMaxHeight()
            .width(300.dp)
            .padding(8.dp)
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