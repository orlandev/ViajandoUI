package com.orlandev.viajandoui.ui.screens.seat_selector

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.SharedViewModel
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme
import com.orlandev.viajandoui.utils.randomChairs
import kotlin.random.Random

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
            SeatModernVariantScreen()
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
        SeatModernVariantScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SeatModernVariantScreen() {

    val seatAvailables = Random.randomChairs()
    val cantPassangers = remember {
        mutableStateOf(1)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "Asientos disponibles",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.ic_seat), contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = seatAvailables, style = MaterialTheme.typography.titleLarge)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Pasajeros",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Row(
                        modifier = Modifier.width(120.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {

                        IconButton(
                            modifier = Modifier
                                .size(50.dp)
                                .weight(4f)
                                .padding(end = 4.dp),
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
                                modifier = Modifier.weight(2f)
                            )
                        }

                        IconButton(
                            modifier = Modifier
                                .size(50.dp)
                                .weight(4f)
                                .padding(end = 4.dp),
                            onClick = {
                                cantPassangers.value++
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
        for (i in 1..cantPassangers.value) {
            item {
                PassengerForm(id = i)
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Pasajero $id")
            Icon(Icons.Default.QrCodeScanner, contentDescription = null)
        }

        TextField(
            label = { Text(text = "Nombre y Apellidos") },
            value = name,
            onValueChange = setName
        )
        TextField(label = { Text(text = "No. identificación") }, value = ci, onValueChange = setCI)

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