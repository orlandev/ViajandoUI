package com.orlandev.viajandoui.ui.screens.new_booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewBookingScreen() {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 18.dp, top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(5f), horizontalAlignment = Alignment.Start) {
                Text(
                    text = stringResource(id = R.string.hi_there),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Light,
                    color = textColor
                )
                Text(
                    text = stringResource(id = R.string.your_itinerary),
                    color = textColor
                )
            }
        }

        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(18.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    SelectorComp(text = "Origen")
                    Divider()
                    SelectorComp(icon = Icons.Default.LocationOn, text = "Destino")
                }

                FloatingActionButton(
                    modifier = Modifier
                        .offset(x = (-16).dp)
                        .size(60.dp)
                        .align(Alignment.CenterEnd),
                    onClick = { /*TODO*/ }

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.reload),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun SelectorComp(
    icon: ImageVector = Icons.Default.TripOrigin,
    text: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Spacer(modifier = Modifier.width(1.dp))
        Icon(icon, contentDescription = null)
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun NewBookingPreview() {
    ViajandoUITheme {
        NewBookingScreen()
    }
}