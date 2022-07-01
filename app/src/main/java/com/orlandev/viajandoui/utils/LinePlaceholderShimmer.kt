package com.orlandev.viajandoui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import kotlin.random.Random

@Composable
fun LinePlaceholderShimmer(
    maxWith: Dp = Dp(
        Random
            .nextInt(90, 400)
            .toFloat()
    )
) {

    Box(
        modifier = Modifier
            .width(
                maxWith
            )
            .height(8.dp)
            .placeholder(
                visible = true,
                color = androidx.compose.material3.MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(4.dp),
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer.copy(
                        alpha = 0.6f
                    ),
                ),
            )
    )
}
