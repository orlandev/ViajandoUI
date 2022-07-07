package com.orlandev.viajandoui.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.orlandev.viajandoui.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val textColor = MaterialTheme.colorScheme.onBackground

    val bottomSheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        bottomSheetState.bottomSheetState.collapse()
    }

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background,
        sheetContent = {
            LazyColumn {
                items(500) {
                    Text(modifier = Modifier.clickable {
                        scope.launch {
                            bottomSheetState.bottomSheetState.collapse()
                        }
                    }, text = "LIST OF PLACES $it")
                }
            }
        }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
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
                Column(modifier = Modifier.weight(5f), horizontalAlignment = Alignment.Start) {
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

            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(18.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SelectorComp(text = "Origen") {
                            scope.launch {
                                bottomSheetState.bottomSheetState.expand()
                            }
                        }
                        Divider()
                        SelectorComp(icon = Icons.Default.LocationOn, text = "Destino") {
                            scope.launch {
                                bottomSheetState.bottomSheetState.expand()
                            }
                        }
                    }

                    FloatingActionButton(
                        modifier = Modifier
                            .offset(x = (-16).dp)
                            .size(60.dp)
                            .align(Alignment.CenterEnd),
                        onClick = {

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

    }


}

@Composable
fun SelectorComp(
    icon: ImageVector = Icons.Default.TripOrigin,
    text: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Spacer(modifier = Modifier.width(1.dp))
        Icon(icon, contentDescription = null)
        Text(text = text)
    }
}