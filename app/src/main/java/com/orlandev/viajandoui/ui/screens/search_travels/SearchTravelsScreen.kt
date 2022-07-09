package com.orlandev.viajandoui.ui.screens.search_travels

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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

            travelData.value?.let { searchDataTravel ->
                TravelHeader(searchDataTravel)
            }
        }

        item {
            TravelTabs()
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
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



@Preview(showBackground = true)
@Composable
fun TravelTabsPreview() {
    ViajandoUITheme {
        TravelTabs()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelTabs() {

    val selected = rememberSaveable {
        mutableStateOf(0)
    }

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
                        selected.value = 0
                    },
                icon = R.drawable.ic_all,
                selected = selected.value == 0,
            )
            TravelTab(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        selected.value = 1
                    },
                icon = R.drawable.ic_bus,
                selected = selected.value == 1,
            )
            TravelTab(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        selected.value = 2
                    },
                icon = R.drawable.ic_train,
                selected = selected.value == 2,

                )
            TravelTab(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        selected.value = 3
                    },
                icon = R.drawable.ic_boat,
                selected = selected.value == 3,
            )
            TravelTab(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
                        selected.value = 4
                    },
                icon = R.drawable.ic_airplane,
                selected = selected.value == 4,
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


@Preview(showBackground = true)
@Composable
fun TravelHeaderPreview() {
    ViajandoUITheme {
        TravelHeader()
    }
}




















