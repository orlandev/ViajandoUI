package com.orlandev.viajandoui.ui.screens.seat_selector

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orlandev.viajandoui.SharedViewModel
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
            Text(text = "LEGACY VARIANT")
        }
        SeatScreenVariants.SeatModernVariant -> {
            Text(text = "MODERN VARIANT")
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

@Composable
fun SeatSelectorVariantScreen(onSelect: (SeatScreenVariants) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Button(onClick = {

                onSelect(SeatScreenVariants.SeatModernVariant)

            }) {
                Text(text = "Seleccion de Asientos Variante 1")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                onSelect(SeatScreenVariants.SeatLegacyVariant)
            }) {
                Text(text = "Seleccion de Asientos Variante 2")
            }
        }
    }
}