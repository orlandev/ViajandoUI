package com.orlandev.viajandoui.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContactPhone
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
fun AboutScreen() {

    Column(Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Image(
                modifier = Modifier
                    .weight(2f),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = null
            )
            Column(modifier = Modifier.weight(5f), horizontalAlignment = Alignment.Start) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = BuildConfig.VERSION_NAME,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            text = stringResource(id = R.string.app_description),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(start = 30.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 10.dp),
            text = stringResource(id = R.string.contact_support),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    ViajandoUITheme {
        AboutScreen()
    }
}