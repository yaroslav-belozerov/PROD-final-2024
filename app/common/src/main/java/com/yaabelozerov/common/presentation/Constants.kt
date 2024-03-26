package com.yaabelozerov.common.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class Constants {
    sealed class Shimmer {
        companion object {
            val duration: Int = 1000
            val colors =
                listOf(
                    Color.hsl(253.71f, 0.5932f, 0.8843f).copy(alpha = 1f),
                    Color.hsl(253.71f, 0.5932f, 0.8843f).copy(alpha = 0.2f),
                    Color.hsl(253.71f, 0.5932f, 0.8843f).copy(alpha = 0.2f),
                    Color.hsl(253.71f, 0.5932f, 0.8843f).copy(alpha = 1f),
                )
            val shape = RoundedCornerShape(8.dp)
        }
    }

    sealed class Fonts {
        companion object {
            val small = 16.sp
            val medium = 22.sp
            val large = 36.sp
            val xLarge = 40.sp

            val largeHeight = 32.sp
            val xLargeHeight = 36.sp
        }
    }

    sealed class ErrorMessages {
        companion object {
            const val UNKNOWN = "Unknown error, please try again later or submit a bug report"
            const val LOCATION =
                "Cannot retrieve location, check if you have location enabled and the app has the required permission"
            const val PLACEHOLDER =
                "Unknown error, please try again or contact support. Lorem ipsum dolor sit amet " +
                    "consectetur adipiscing elit. Donec ut nunc et sem ultrices auctor. "
        }
    }

    companion object {
        const val DEGREE_SYMBOL = "°"
        const val DISTANCE_SYMBOL = "m"
        const val DEGREE_QUALIFIER = "C"
    }
}
