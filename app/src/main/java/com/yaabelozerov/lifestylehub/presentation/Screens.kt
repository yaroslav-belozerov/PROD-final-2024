package com.yaabelozerov.lifestylehub.presentation

sealed class Screens(val route: String) {
    object MainScreen : Screens("MainScreen")

    object NotesScreen : Screens("NotesScreen")

    object ProfileScreen : Screens("ProfileScreen")
}
