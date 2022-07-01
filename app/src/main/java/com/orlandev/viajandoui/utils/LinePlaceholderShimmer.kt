package com.orlandev.viajandoui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import kotlin.random.Random

@Composable
fun LinePlaceholderShimmer(
    maxWith: Dp = Dp(
        Random
            .nextInt(90, 500)
            .toFloat()
    ),
    minHeight: Dp = 8.dp,
    paddingValues: PaddingValues=PaddingValues(8.dp, 8.dp, 8.dp, 8.dp),
) {

    Box(
        modifier = Modifier
            .height(minHeight)
            .width(
                maxWith
            )
            .placeholder(
                visible = true,
                color = androidx.compose.material3.MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(4.dp),
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer.copy(
                        alpha = 0.6f
                    ),
                ),
            ).padding(paddingValues),
    )
}
