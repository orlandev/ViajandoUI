package com.orlandev.viajandoui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TravelLeftRoute(modifier: Modifier = Modifier, icon: Int) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(id = icon),
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
}
