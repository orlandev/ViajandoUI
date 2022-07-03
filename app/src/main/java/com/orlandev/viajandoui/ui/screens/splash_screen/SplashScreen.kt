package com.orlandev.viajandoui.ui.screens.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orlandev.viajandoui.BuildConfig
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(25.dp),

            ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo",
                contentScale = ContentScale.FillBounds,
            )

            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = BuildConfig.VERSION_NAME,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            LinearProgressIndicator(modifier = Modifier.width(170.dp).height(1.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_bus), contentDescription = "BUS")
                Icon(
                    painter = painterResource(id = R.drawable.ic_train),
                    contentDescription = "TRAIN"
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_boat),
                    contentDescription = "BOAT"
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_airplane),
                    contentDescription = "AIRPLANE"
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {

    ViajandoUITheme {
        SplashScreen()
    }

}