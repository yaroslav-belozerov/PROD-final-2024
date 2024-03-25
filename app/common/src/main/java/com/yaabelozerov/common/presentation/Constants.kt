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
                    Color.LightGray.copy(alpha = 0.9f),
                    Color.LightGray.copy(alpha = 0.3f),
                    Color.LightGray.copy(alpha = 0.3f),
                    Color.LightGray.copy(alpha = 0.9f),
                )
            val shape = RoundedCornerShape(8.dp)
        }
    }

    sealed class Fonts {
        companion object {
            val small = 16.sp
            val medium = 22.sp
            val large = 36.sp
        }
    }

    sealed class ErrorMessages {
        companion object {
            const val UNKNOWN = "Unknown error"
            const val LOCATION = "Cannot retrieve location, check you permissions"
        }
    }

    companion object {
        const val DEGREE_SYMBOL = "°"
    }
}
