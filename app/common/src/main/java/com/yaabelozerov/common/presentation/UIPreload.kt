package com.yaabelozerov.common.presentation

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerSpacer(
    width: Float,
    height: Float,
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier =
            Modifier.width(width.dp).height(height.dp).clip(shape = CommonConstants.Shimmer.shape)
                .shimmer(
                    CommonConstants.Shimmer.colors,
                ).then(modifier),
    )
}

fun Modifier.shimmer(colors: List<Color>): Modifier =
    composed {
        var size by remember {
            mutableStateOf(
                IntSize.Zero,
            )
        }
        val transition = rememberInfiniteTransition(label = "Shimmer")
        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec =
                infiniteRepeatable(
                    animation = tween(CommonConstants.Shimmer.duration),
                ),
            label = "startOffsetX",
        )

        background(
            brush =
                Brush.horizontalGradient(
                    colors = colors,
                    startX = startOffsetX,
                    endX = startOffsetX + size.width.toFloat(),
                ),
        ).onGloballyPositioned {
            size = it.size
        }
    }
