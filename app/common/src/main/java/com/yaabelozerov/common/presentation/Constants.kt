package com.yaabelozerov.common.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class Constants {
    sealed class Shimmer {
        companion object {
            val duration: Int = 1000
            val colors = listOf(
                Color.LightGray.copy(alpha = 0.9f),
                Color.LightGray.copy(alpha = 0.3f),
                Color.LightGray.copy(alpha = 0.3f),
                Color.LightGray.copy(alpha = 0.9f)
            )
            val shape = RoundedCornerShape(8.dp)
        }
    }

    sealed class Fonts {
        companion object {
            val small = 12.sp
            val medium = 20.sp
        }
    }
}