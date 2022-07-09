package com.orlandev.viajandoui.ui.screens.home

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.SharedViewModel
import com.orlandev.viajandoui.model.PasajeSelection
import com.orlandev.viajandoui.model.SearchTravelModel
import com.orlandev.viajandoui.model.TravelType
import com.orlandev.viajandoui.navigation.NavRouter
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme
import kotlinx.coroutines.launch
import java.util.*


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
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val textColor = MaterialTheme.colorScheme.onBackground

    val scope = rememberCoroutineScope()

    val selection = remember {
        mutableStateOf(PasajeSelection.ORIGIN)
    }

    val (origin, setOrigin) = remember {
        mutableStateOf("")
    }

    val (destiny, setDestiny) = remember {
        mutableStateOf("")
    }

    val backdropState = rememberBackdropScaffoldState(BackdropValue.Revealed)

    val halfHeightDp = Dp((LocalConfiguration.current.screenHeightDp / 6).toFloat())

    val selectorTripType = remember {
        mutableStateOf(TravelType.SOLO_IDA)
    }

    val mDateIda = remember { mutableStateOf("") }

    val mDateIdaRegreso = remember { mutableStateOf("") }

    val errorDataNotSpecify = remember {
        mutableStateOf(false)
    }

    if (errorDataNotSpecify.value)
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                Button(onClick = { errorDataNotSpecify.value = false }) {
                    Text(text = stringResource(id = R.string.button_text_acept))
                }
            },
            title = { Text(text = stringResource(id = R.string.app_name)) },
            text = {
                Text(text = stringResource(id = R.string.error_data_empty_in_new_booking))
            }
        )

    BackdropScaffold(
        backLayerBackgroundColor = MaterialTheme.colorScheme.background,
        frontLayerBackgroundColor = MaterialTheme.colorScheme.background,
        scaffoldState = backdropState,
        gesturesEnabled = false,
        appBar = {},
        headerHeight = 0.dp,
        peekHeight = halfHeightDp,
        backLayerContent = {

            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
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
                        Column(
                            modifier = Modifier.weight(5f),
                            horizontalAlignment = Alignment.Start
                        ) {
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
                }

                item {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                IconTextSelector(text = origin.ifEmpty { stringResource(id = R.string.origin_text) }) {
                                    scope.launch {
                                        selection.value = PasajeSelection.ORIGIN
                                        backdropState.conceal()
                                    }
                                }
                                Divider()
                                IconTextSelector(
                                    icon = Icons.Default.LocationOn,
                                    text = destiny.ifEmpty {
                                        stringResource(
                                            id = R.string.destiny_text
                                        )
                                    }) {
                                    selection.value = PasajeSelection.DESTINY
                                    scope.launch {
                                        backdropState.conceal()
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

                item {

                    //Seleccion de ida y regreso
                    Selector(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                    ) {
                        selectorTripType.value = it
                    }
                }

                item {

                    CalendarSelector(
                        text = mDateIda.value.ifEmpty { stringResource(id = R.string.fecha_ida) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        callBackDate = mDateIda,
                    )
                }

                item {
                    AnimatedVisibility(
                        visible = selectorTripType.value == TravelType.IDA_REGRESO,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {

                        CalendarSelector(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            text = mDateIdaRegreso.value.ifEmpty { stringResource(id = R.string.fecha_regreso) },
                            callBackDate = mDateIdaRegreso
                        )
                    }
                }
                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {

                            if (origin.isEmpty() || destiny.isEmpty() || mDateIda.value.isEmpty()) {
                                errorDataNotSpecify.value = true
                                return@Button
                            }

                            val newTravel = SearchTravelModel(
                                origin = origin,
                                destiny = destiny,
                                dateIda = mDateIda.value,
                                dateIdaYRegreso = mDateIdaRegreso.value,
                                travelType = selectorTripType.value
                            )
                            sharedViewModel.onSetTravelData(newTravel = newTravel)

                            navController.navigate(NavRouter.SearchTravelsRoute.route)

                        }) {
                        Text(
                            text = stringResource(id = R.string.search_button),
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        },
        frontLayerContent = {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clickable {
                                scope.launch {
                                    backdropState.reveal()
                                }
                            }
                        //  .background(MaterialTheme.colorScheme.background)
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
                }
                items(listOfRoutes.sortedBy { it }
                    .filter { it != origin && it != destiny }) { route ->
                    Card(
                        colors = cardColors(
                            containerColor = MaterialTheme.colorScheme.background,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .clickable {

                                if (selection.value == PasajeSelection.ORIGIN) {
                                    setOrigin(route)

                                } else {
                                    setDestiny(route)
                                }

                                scope.launch {
                                    backdropState.reveal()
                                }
                            }
                    ) {
                        Text(modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            , text = route
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarSelector(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.fecha_ida),
    callBackDate: MutableState<String>,


    ) {
    //Necesario para el calendarios

    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format


    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            callBackDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    //Necesario para el calendarios


    OutlinedCard(
        modifier = modifier
    ) {
        IconTextSelector(icon = Icons.Default.CalendarMonth, text = text) {
            mDatePickerDialog.show()
        }
    }
}

@Composable
fun IconTextSelector(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
    alignment: Arrangement.HorizontalOrVertical? = null,
    icon: ImageVector? = Icons.Default.TripOrigin,
    text: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = alignment ?: Arrangement.spacedBy(10.dp),

        ) {
        Spacer(modifier = Modifier.width(1.dp))
        if (icon != null)
            Icon(icon, contentDescription = null)
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Selector(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .padding(8.dp), onSelection: (TravelType) -> Unit
) {

    //TRUE -> Ida y regreso
    //FALSE -> Solo ida

    val selectable = remember {
        mutableStateOf(TravelType.SOLO_IDA)
    }

    OutlinedCard(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            //TODO Add Font to text

            IconTextSelector(
                icon = if (selectable.value == TravelType.SOLO_IDA) Icons.Default.Check else null,
                modifier = Modifier
                    .fillMaxHeight()
                    .background(if (selectable.value == TravelType.SOLO_IDA) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.background)
                    .weight(1f),
                text = "Solo ida",
                alignment = Arrangement.Center
            )
            {
                selectable.value = TravelType.SOLO_IDA
                onSelection(TravelType.SOLO_IDA)
            }
            Divider(
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            IconTextSelector(
                icon = if (selectable.value == TravelType.IDA_REGRESO) Icons.Default.Check else null,
                modifier = Modifier
                    .fillMaxHeight()
                    .background(if (selectable.value == TravelType.IDA_REGRESO) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.background)
                    .weight(1f),
                text = "Ida y Regreso",
                alignment = Arrangement.Center
            ) {
                selectable.value = TravelType.IDA_REGRESO
                onSelection(TravelType.IDA_REGRESO)
            }

        }

    }

}

@Preview
@Composable
fun SelectorPreview() {
    ViajandoUITheme {
        Selector(onSelection = {})
    }
}


