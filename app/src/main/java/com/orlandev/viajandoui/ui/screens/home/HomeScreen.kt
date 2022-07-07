package com.orlandev.viajandoui.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.orlandev.viajandoui.R
import kotlinx.coroutines.launch

enum class PasajeSelection {
    ORIGIN,
    DESTINY
}

val listOfRoutes = listOf<String>(
    "Pinar del Río",
    "Artemisa",
    "La Habana",
    "Mayabeque",
    "Matanzas",
    "Cienfuegos",
    "Villa Clara",
    "Sancti Spíritus",
    "Ciego de Ávila",
    "Camagüey",
    "Las Tunas",
    "Granma",
    "Holguín",
    "Santiago de Cuba",
    "Guantánamo",
    "Isla de la Juventud",
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val textColor = MaterialTheme.colorScheme.onBackground

    val selection = rememberSaveable {
        mutableStateOf(PasajeSelection.ORIGIN)
    }
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        bottomSheetState.bottomSheetState.collapse()
    }

    val (origin, setOrigin) = remember {
        mutableStateOf("")
    }

    val (destiny, setDestiny) = remember {
        mutableStateOf("")
    }

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()

                    .height(10.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(50))
                        .align(Alignment.Center)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)
            ) {
                (listOfRoutes.sortedBy { it }
                    .filter { it != origin && it != destiny }).forEach { route ->
                        Text(modifier = Modifier.clickable {

                            if (selection.value == PasajeSelection.ORIGIN) {
                                setOrigin(route)

                            } else {
                                setDestiny(route)
                            }

                            scope.launch {
                                bottomSheetState.bottomSheetState.collapse()
                            }
                        }, text = route)
                    }
                }

        }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
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
                        SelectorComp(text = origin.ifEmpty { stringResource(id = R.string.origin_text) }) {
                            scope.launch {
                                selection.value = PasajeSelection.ORIGIN
                                bottomSheetState.bottomSheetState.expand()
                            }
                        }
                        Divider()
                        SelectorComp(icon = Icons.Default.LocationOn, text = destiny.ifEmpty {
                            stringResource(
                                id = R.string.destiny_text
                            )
                        }) {
                            selection.value = PasajeSelection.DESTINY
                            scope.launch {
                                bottomSheetState.bottomSheetState.expand()
                            }
                        }
                    }

                    FloatingActionButton(
                        modifier = Modifier
                            .offset(x = (-16).dp)
                            .size(60.dp)
                            .align(Alignment.CenterEnd),
                        onClick = {

                            setOrigin(destiny)
                            setDestiny(origin)

                        }

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