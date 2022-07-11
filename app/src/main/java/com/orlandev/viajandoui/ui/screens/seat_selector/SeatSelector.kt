package com.orlandev.viajandoui.ui.screens.seat_selector

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.material3.BottomAppBarDefaults.floatingActionButtonElevation
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.SharedViewModel
import com.orlandev.viajandoui.ui.screens.search_travels.TravelCard
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme

sealed class SeatScreenVariants() {
    object SeatSelectorVariant : SeatScreenVariants()
    object SeatModernVariant : SeatScreenVariants()
    object SeatLegacyVariant : SeatScreenVariants()
}

@Composable
fun SeatSelectorScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val currentVariant = remember {
        mutableStateOf<SeatScreenVariants>(SeatScreenVariants.SeatSelectorVariant)
    }

    when (currentVariant.value) {
        SeatScreenVariants.SeatSelectorVariant -> {
            SeatSelectorVariantScreen() {
                currentVariant.value = it
            }
        }
        SeatScreenVariants.SeatLegacyVariant -> {
            SeatLegacyVariantScreen()
        }
        SeatScreenVariants.SeatModernVariant -> {
            SeatModernVariantScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}

@Preview
@Composable
fun SelectorScreenPreview() {
    ViajandoUITheme {
        SeatSelectorVariantScreen({})
    }
}

@Preview
@Composable
fun SeatLegacyVariantPreview() {
    ViajandoUITheme {
        SeatLegacyVariantScreen()
    }
}

@Preview
@Composable
fun SeatModernVariantPreview() {
    ViajandoUITheme {
        SeatModernVariantScreen(null)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SeatModernVariantScreen(
    navController: NavController?,
    sharedViewModel: SharedViewModel? = null
) {

    val cantPassangers = remember {
        mutableStateOf(1)
    }

    val passage = sharedViewModel?.currentPassage?.collectAsState()

    val paymentCount = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(cantPassangers.value) {
        passage?.value?.let { currentPassage ->
            paymentCount.value = currentPassage.price * cantPassangers.value
        }
    }

    passage?.value?.let { currentPassage ->

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    elevation = floatingActionButtonElevation(
                        defaultElevation = 2.dp,
                        ),
                    modifier = Modifier
                        .animateContentSize(),
                    onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Payment, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "$${paymentCount.value}.00")
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues(horizontal = 16.dp)),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                item {
                    TravelCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        passage = currentPassage,
                    )
                }

                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.seat_avalaible),
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        painterResource(id = R.drawable.ic_seat),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = currentPassage.cantSeatsAvailable.toString(),
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = stringResource(id = R.string.passengers),
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Row(
                                    modifier = Modifier.width(130.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {

                                    IconButton(
                                        enabled = cantPassangers.value > 1,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(40.dp),
                                        colors = iconButtonColors(
                                            contentColor = MaterialTheme.colorScheme.onPrimary,
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(
                                                alpha = 0.5f
                                            ),
                                        ),
                                        onClick = {
                                            if (cantPassangers.value > 1) {
                                                cantPassangers.value--
                                            }
                                        }) {
                                        Icon(
                                            Icons.Filled.Remove,
                                            contentDescription = null,
                                        )
                                    }

                                    AnimatedContent(
                                        modifier = Modifier.weight(4f),
                                        targetState = cantPassangers.value,
                                        transitionSpec = {
                                            // Compare the incoming number with the previous number.
                                            if (targetState > initialState) {
                                                // If the target number is larger, it slides up and fades in
                                                // while the initial (smaller) number slides up and fades out.
                                                slideInVertically(
                                                    initialOffsetY = { height -> height }) + fadeIn() with
                                                        slideOutVertically(targetOffsetY = { height -> -height }) + fadeOut()
                                            } else {
                                                // If the target number is smaller, it slides down and fades in
                                                // while the initial number slides down and fades out.
                                                slideInVertically(initialOffsetY = { height -> -height }) + fadeIn() with
                                                        slideOutVertically(targetOffsetY = { height -> height }) + fadeOut()
                                            }.using(
                                                // Disable clipping since the faded slide-in/out should
                                                // be displayed out of bounds.
                                                SizeTransform(clip = false)
                                            )
                                        }
                                    ) { targetCount ->
                                        Text(
                                            text = "$targetCount",
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.weight(2f),
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                    }

                                    IconButton(
                                        enabled = cantPassangers.value < (passage.value?.cantSeatsAvailable
                                            ?: 2),
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(40.dp),
                                        colors = iconButtonColors(
                                            contentColor = MaterialTheme.colorScheme.onPrimary,
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(
                                                alpha = 0.5f
                                            ),
                                        ),
                                        onClick = {
                                            if (cantPassangers.value < (passage.value?.cantSeatsAvailable
                                                    ?: 2)
                                            ) {
                                                cantPassangers.value++
                                            }
                                        }) {
                                        Icon(
                                            Icons.Filled.Add,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                for (i in 1..cantPassangers.value) {
                    item {
                        PassengerForm(id = i)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerForm(id: Int) {

    val (name, setName) = remember {
        mutableStateOf("")
    }
    val (ci, setCI) = remember {
        mutableStateOf("")
    }
    Card(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.passenger) + "$id")
                Icon(Icons.Default.QrCodeScanner, contentDescription = null)
            }



            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(text = stringResource(id = R.string.name_lastname)) },
                value = name,
                onValueChange = setName
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.user_ci)
                    )
                },
                value = ci, onValueChange = setCI
            )

            Spacer(modifier = Modifier.height(8.dp))
        }


    }

}


@Composable
fun SeatLegacyVariantScreen() {
    Text(text = "LEGACY VARIANT")
}

@Composable
fun SeatSelectorVariantScreen(onSelect: (SeatScreenVariants) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Button(onClick = {

                onSelect(SeatScreenVariants.SeatModernVariant)

            }) {
                Text(text = stringResource(id = R.string.text_seat_selectior_variant_1))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                onSelect(SeatScreenVariants.SeatLegacyVariant)
            }) {
                Text(text = stringResource(id = R.string.text_seat_selector_variant_2))
            }
        }
    }
}