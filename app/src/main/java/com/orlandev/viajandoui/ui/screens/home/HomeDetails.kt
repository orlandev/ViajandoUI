package com.orlandev.viajandoui.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.utils.GradientEffect

@Composable
fun HomeDetails(viewModel: HomeViewModel = hiltViewModel(), id: String) {

    Log.d("UUID", "PARAM $id")
    val news = viewModel.listOfNews.firstOrNull() {
        Log.d("UUID", "FILTER ${it.id}")
        it.id == id
    }

    if (news != null) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                HomeDetailsHeader(
                    title = news.title,
                    imageUrl = news.imageUrl
                )
            }

            when (news) {
                is ViajandoNewsType.Event -> {
                    item {
                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                            text = news.schedule
                        )
                    }
                }
                is ViajandoNewsType.Offer -> {
                    item {
                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                            text = "$${news.price}"
                        )
                    }
                }
            }

            item {
                HomeDetailsBody(description = news.description)
            }

            item {
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Sin Datos")
        }
    }
}

@Composable
fun HomeDetailsBody(description: String?) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        text = description ?: "",
        style = MaterialTheme.typography.bodyMedium
    )

}

@Composable
fun HomeDetailsHeader(title: String?, imageUrl: String) {

    val loadingImage = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
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
            model = imageUrl.ifEmpty { R.drawable.alerta_viajando }, contentDescription = null
        )

        GradientEffect(backgroundColor = MaterialTheme.colorScheme.background)

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            text = title ?: "",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

