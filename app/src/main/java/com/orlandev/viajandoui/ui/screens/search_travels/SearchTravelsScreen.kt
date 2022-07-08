package com.orlandev.viajandoui.ui.screens.search_travels

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.SharedViewModel
import com.orlandev.viajandoui.model.SearchTravelModel
import com.orlandev.viajandoui.model.TravelTransportType
import com.orlandev.viajandoui.model.TravelType
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme
import com.orlandev.viajandoui.utils.RandomChairs
import com.orlandev.viajandoui.utils.RandomCities
import com.orlandev.viajandoui.utils.RandomPrice
import com.orlandev.viajandoui.utils.RandomTime
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

        travelData.value?.let { searchDataTravel ->
            item {
                TravelHeader(searchDataTravel)
            }
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

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelHeader(travelData: SearchTravelModel? = null) {

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = travelData?.origin ?: " - ",
                style = MaterialTheme.typography.titleMedium
            )

            if (travelData?.travelType == TravelType.SOLO_IDA)
                Icon(Icons.Default.DoubleArrow, contentDescription = null)
            else
                Icon(Icons.Default.CompareArrows, contentDescription = null)

            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                text = travelData?.destiny ?: " - ",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        //TODO Refactor this component
        if (travelData?.dateIdaYRegreso != null && travelData.dateIdaYRegreso.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = travelData.dateIda ?: " - ",
                    style = MaterialTheme.typography.titleMedium
                )

                if (travelData.travelType == TravelType.SOLO_IDA)
                    Icon(Icons.Default.DoubleArrow, contentDescription = null)
                else
                    Icon(Icons.Default.CompareArrows, contentDescription = null)

                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    text = travelData.dateIdaYRegreso,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = travelData?.dateIda ?: " - ",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TravelHeaderPreview() {
    ViajandoUITheme {
        TravelHeader()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelCard(travelTransportType: TravelTransportType) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 4.dp)
                    .weight(1.1f),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(text = RandomTime(), style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = RandomTime(), style = MaterialTheme.typography.labelSmall)

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .padding(2.dp)
                    .weight(1f),
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
                    .padding(start = 2.dp)
                    .weight(3f),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Text(text = RandomCities())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = RandomCities())

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 8.dp)
                    .weight(2f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End

            ) {

                Row(modifier = Modifier) {
                    Text(text = RandomChairs())
                    Icon(painterResource(id = R.drawable.ic_seat), contentDescription = null)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = RandomPrice())

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























