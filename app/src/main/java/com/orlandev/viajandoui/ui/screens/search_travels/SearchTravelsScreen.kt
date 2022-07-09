package com.orlandev.viajandoui.ui.screens.search_travels

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchTravelsScreen(
    navController: NavController, sharedViewModel: SharedViewModel
) {

    val travelData = sharedViewModel.travelData.collectAsState()

    val haveData = remember {
        mutableStateOf(false)
    }

    val travelT = remember {
        mutableStateListOf<TravelTransportType>()
    }

    val filterTravel = remember {
        mutableStateOf<TravelTransportType>(TravelTransportType.Bus)
    }


    LaunchedEffect(Unit) {
        //TODO Simulating Requesting server information
        (1..20).forEach { _ ->
            travelT.add(
                when (Random.nextInt(0, 4)) {
                    0 -> TravelTransportType.Airplane
                    1 -> TravelTransportType.Boat
                    2 -> TravelTransportType.Train
                    3 -> TravelTransportType.Bus
                    else -> TravelTransportType.Airplane
                }
            )
        }
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

            travelData.value?.let { searchDataTravel ->
                TravelHeader(searchDataTravel)
            }
        }

        item {
            TravelTabs(filterTravel)

        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(Icons.Default.DoubleArrow, contentDescription = null)

                    Text(
                        text = stringResource(id = R.string.travel_not_avaliable) + " ${travelData.value?.dateIda}"
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Divider(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.travel_sugestions)
                )

                Divider(modifier = Modifier.weight(1f))

            }
        }

        items(travelT.filter { it == filterTravel.value }, key = { it }) { currentTravel ->
            TravelCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                travelTransportType = currentTravel
            )
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelTabs(selected: MutableState<TravelTransportType>) {

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            TravelTab(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        selected.value = TravelTransportType.Bus
                    },
                icon = R.drawable.ic_bus,
                selected = selected.value == TravelTransportType.Bus,
            )
            TravelTab(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        selected.value = TravelTransportType.Train
                    },
                icon = R.drawable.ic_train,
                selected = selected.value == TravelTransportType.Train,
            )
            TravelTab(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        selected.value = TravelTransportType.Boat
                    },
                icon = R.drawable.ic_boat,
                selected = selected.value == TravelTransportType.Boat,
            )
            TravelTab(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        selected.value = TravelTransportType.Airplane
                    },
                icon = R.drawable.ic_airplane,
                selected = selected.value == TravelTransportType.Airplane,
            )
        }
    }
}


@Composable
fun TravelTab(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    selected: Boolean = false
) {

    val iconTint =
        if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
    val backgroundColor =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
    Box(modifier = modifier.background(backgroundColor), contentAlignment = Alignment.Center) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconTint
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelHeader(travelData: SearchTravelModel? = null) {
    Card(
        colors = cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(

                    vertical = 4.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(2f)
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.origin_text),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = travelData?.origin ?: " - ",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .weight(1f)
            )

            Column(
                modifier = Modifier
                    .weight(2f)
                    .height(60.dp), horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = stringResource(id = R.string.destiny_text),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    text = travelData?.destiny ?: " - ",
                    style = MaterialTheme.typography.titleMedium
                )
            }

        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            if (travelData?.travelType == TravelType.SOLO_IDA)
                Icon(Icons.Default.DoubleArrow, contentDescription = null)
            else {
                Row(modifier = Modifier) {
                    Icon(
                        Icons.Default.DoubleArrow,
                        modifier = Modifier.rotate(180f),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(Icons.Default.DoubleArrow, contentDescription = null)
                }
            }
        }

        //TODO Refactor this component

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Text(text = "Ida", style = MaterialTheme.typography.labelSmall)
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = travelData?.dateIda ?: "Sin definir",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            /*  if (travelData.travelType == TravelType.SOLO_IDA)
                  Icon(Icons.Default.DoubleArrow, contentDescription = null)
              else {
                  Row(modifier = Modifier) {
                      Icon(
                          Icons.Default.DoubleArrow,
                          modifier = Modifier.rotate(180f),
                          contentDescription = null
                      )
                      Spacer(modifier = Modifier.width(10.dp))
                      Icon(Icons.Default.DoubleArrow, contentDescription = null)
                  }
              }*/

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = "Regreso", style = MaterialTheme.typography.labelSmall)
                Text(
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.End,
                    text = travelData?.dateIdaYRegreso?.ifEmpty { "Sin definir" }
                        ?: "Sin definir",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelCard(modifier: Modifier, travelTransportType: TravelTransportType) {

    Card(
        modifier = modifier,
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
fun TravelHeaderPreview() {
    ViajandoUITheme {
        TravelHeader()
    }
}




















