package com.orlandev.viajandoui.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orlandev.viajandoui.BuildConfig
import com.orlandev.viajandoui.R
import com.orlandev.viajandoui.ui.theme.ViajandoUITheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Telegram
import compose.icons.fontawesomeicons.brands.Twitter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    val scrollState = rememberScrollState()
    val textColor = MaterialTheme.colorScheme.onBackground
    val urlHandle = LocalUriHandler.current

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

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
                    style = MaterialTheme.typography.headlineMedium, color = textColor

                )
                Text(
                    text = BuildConfig.VERSION_NAME,
                    style = MaterialTheme.typography.bodySmall, color = textColor
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            text = stringResource(id = R.string.app_description),
            style = MaterialTheme.typography.bodyMedium, color = textColor
        )

        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(horizontal = 30.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 10.dp),
            text = stringResource(id = R.string.contact_support),
            style = MaterialTheme.typography.titleMedium, color = textColor
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp),
            text = stringResource(id = R.string.contact_schedule),
            style = MaterialTheme.typography.titleSmall, color = textColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {

            ContactItem(
                info = stringResource(id = R.string.contact_support_phone),
                urlHandle = urlHandle
            )

            ContactItem(
                info = stringResource(id = R.string.contact_support_phone2),
                urlHandle = urlHandle
            )

            ContactItem(
                info = stringResource(id = R.string.contact_support_phone3),
                urlHandle = urlHandle
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {

            ContactItem(
                icon = Icons.Outlined.MailOutline,
                info = stringResource(id = R.string.contact_email),
                contactType = ContactType.Email,
                urlHandle = urlHandle
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            CardSocialMedias(urlHandle = urlHandle)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.copyright),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center, color = textColor

            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.company_name),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center, color = textColor

            )
        }

        Spacer(modifier = Modifier.height(30.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSocialMedias(urlHandle: UriHandler) {

    val iconSize = 40.dp
    val textColor = MaterialTheme.colorScheme.onBackground

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceAround
    ) {
        val urlFacebook = stringResource(id = R.string.facebook_url)
        val urlTwitter = stringResource(id = R.string.twitter_url)
        val urlTelegram = stringResource(id = R.string.telegram_url)

        Card(modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                urlHandle.openUri(
                    Uri
                        .parse(urlFacebook)
                        .toString()
                )
            }
            .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = FontAwesomeIcons.Brands.Facebook,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Facebook",
                    style = MaterialTheme.typography.titleSmall,
                    color = textColor
                )
            }
        }

        Card(modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable {

                urlHandle.openUri(
                    Uri
                        .parse(urlTwitter)
                        .toString()
                )

            }
            .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = FontAwesomeIcons.Brands.Twitter,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary

                )
                Text(
                    text = "Twitter",
                    style = MaterialTheme.typography.titleSmall,
                    color = textColor
                )
            }
        }

        Card(modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable {

                urlHandle.openUri(
                    Uri
                        .parse(urlTelegram)
                        .toString()
                )

            }
            .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = FontAwesomeIcons.Brands.Telegram,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Telegram",
                    style = MaterialTheme.typography.titleSmall,
                    color = textColor
                )
            }
        }


    }
}


@Composable
fun ContactItem(
    icon: ImageVector = Icons.Outlined.Phone,
    info: String,
    contactType: ContactType = ContactType.Phone,
    tintColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    urlHandle: UriHandler
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                val start = if (contactType == ContactType.Phone) "tel:" else "mailto:"
                urlHandle.openUri(
                    Uri
                        .parse("$start:$info")
                        .toString()
                )
            },
    ) {
        Icon(
            icon,
            contentDescription = null, tint = tintColor

        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = info,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor

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

enum class ContactType {
    Phone, Email
}