package com.orlandev.viajandoui.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(1000){
            Text(text = "ASDFGHJK")
        }
    }
}