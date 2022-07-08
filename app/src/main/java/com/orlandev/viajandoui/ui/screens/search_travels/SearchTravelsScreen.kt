package com.orlandev.viajandoui.ui.screens.search_travels

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orlandev.viajandoui.SharedViewModel
import com.orlandev.viajandoui.model.SearchTravelModel
import com.orlandev.viajandoui.model.TravelTransportType
import com.orlandev.viajandoui.ui.screens.home.listOfRoutes
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SearchTravelsScreen(
    navController: NavController, sharedViewModel: SharedViewModel
) {

    val travelData = sharedViewModel.travelData.collectAsState()

    val haveData = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        //TODO Simulating Requesting server information

        delay(1500)
        haveData.value = true
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        item {
            TravelHeader(travelData)
        }

        items(10) {
            val travelT = when (Random.nextInt(0, 4)) {
                0 -> TravelTransportType.Airplane
                1 -> TravelTransportType.Boat
                2 -> TravelTransportType.Train
                3 -> TravelTransportType.Bus
                else -> TravelTransportType.Airplane
            }
            TravelCard(travelTransportType = travelT)
        }
    }

}

@Composable
fun TravelHeader(travelData: State<SearchTravelModel?>) {


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelCard(travelTransportType: TravelTransportType) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(text = RandomTime())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = RandomTime())

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(50.dp)
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painterResource(id = travelTransportType.icon),
                    contentDescription = null,
                    modifier = Modifier.weight(
                        2f
                    )
                )
                Divider(
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp)
                )
                Icon(
                    Icons.Outlined.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.weight(
                        2f
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(text = RandomCities())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = RandomCities())

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TravelCardPreview() {
    ViajandoUITheme {
        TravelCard(TravelTransportType.Train)
    }
}


fun RandomTime(): String {
    val hour = Random.nextInt(0, 12)
    val minute = Random.nextInt(0, 60)
    val M = if (Random.nextInt(0, 1) == 0) "AM" else "PM"
    val hourString = if(hour<10) "0$hour" else hour.toString()
    val minureString = if(minute<10) "0$minute" else minute.toString()
    return "$hourString:$minureString$M"
}

fun RandomCities(): String {
    val city1 = Random.nextInt(0, listOfRoutes.size)
    return listOfRoutes[city1]
}
























