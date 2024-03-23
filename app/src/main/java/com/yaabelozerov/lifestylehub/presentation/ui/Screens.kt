package com.yaabelozerov.lifestylehub.presentation.ui

sealed class Screens(val route: String) {
    object MainScreen : Screens("MainScreen")

    object NotesScreen : Screens("NotesScreen")

    object ProfileScreen : Screens("ProfileScreen")
}
