package com.orlandev.viajandoui.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orlandev.viajandoui.BuildConfig
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    val scrollState= rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)) {

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
        Divider(modifier = Modifier.padding(horizontal = 30.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 10.dp),
            text = stringResource(id = R.string.contact_support),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            text = stringResource(id = R.string.contact_schedule),
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {

            ContactItem(phone = stringResource(id = R.string.contact_support_phone)) {
                //TODO MAKE A CALL
            }

            ContactItem(phone = stringResource(id = R.string.contact_support_phone2)) {
                //TODO MAKE A CALL
            }

            ContactItem(phone = stringResource(id = R.string.contact_support_phone3)) {
                //TODO MAKE A CALL
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {

            ContactItem(
                icon = Icons.Outlined.MailOutline,
                phone = stringResource(id = R.string.contact_email)
            ) {
                //TODO MAKE A CALL
            }
        }

        
        
        

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.copyright),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center

            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.company_name),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center

            )
        }

    }
}

@Composable
fun CardSocialMedias() {
    
}

@Composable
fun ContactItem(icon: ImageVector = Icons.Outlined.Phone, phone: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            },
    ) {
        Icon(
            icon,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = phone,
            style = MaterialTheme.typography.bodyMedium
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