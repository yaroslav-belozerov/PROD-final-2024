package com.yaabelozerov.lifestylehub.presentation.ui

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailsScreen(navController: NavController) {
    Button(onClick = { navController.popBackStack() }) {
    }
}
