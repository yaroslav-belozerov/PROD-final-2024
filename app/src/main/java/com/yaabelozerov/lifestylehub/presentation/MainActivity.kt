package com.yaabelozerov.lifestylehub.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yaabelozerov.lifestylehub.presentation.ui.MainScreen
import com.yaabelozerov.lifestylehub.presentation.ui.NotesScreen
import com.yaabelozerov.lifestylehub.presentation.ui.ProfileScreen
import com.yaabelozerov.venues.presentation.VenuesCardViewModel
import com.yaabelozerov.weather.presentation.WeatherCardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1,
            )
        }

        setContent {
            val navController = rememberNavController()
            val items =
                listOf(
                    BottomNavigationBarItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        route = Screens.MainScreen.route,
                    ),
                    BottomNavigationBarItem(
                        title = "Favourites",
                        selectedIcon = Icons.Filled.Favorite,
                        unselectedIcon = Icons.Outlined.FavoriteBorder,
                        route = Screens.NotesScreen.route,
                    ),
                    BottomNavigationBarItem(
                        title = "Profile",
                        selectedIcon = Icons.Filled.AccountCircle,
                        unselectedIcon = Icons.Outlined.AccountCircle,
                        route = Screens.ProfileScreen.route,
                    ),
                )

            val weatherCardViewModel: WeatherCardViewModel = hiltViewModel()
            val venuesCardViewModel: VenuesCardViewModel = hiltViewModel()
            val w by weatherCardViewModel.weather.collectAsState()
            val v by venuesCardViewModel.venues.collectAsState()
            weatherCardViewModel.loadWeatherInfo()
            venuesCardViewModel.loadVenues()

            var selectedIndex by rememberSaveable {
                mutableStateOf(0)
            }
            Scaffold(bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(selected = selectedIndex == index, onClick = {
                            selectedIndex = index
                            navController.navigate(item.route) {
                                popUpTo(item.route) { inclusive = true }
                            }
                        }, icon = {
                            Icon(
                                imageVector = if (selectedIndex == index) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title,
                            )
                        }, label = { Text(text = item.title) })
                    }
                }
            }) {
                NavHost(
                    navController = navController,
                    startDestination = Screens.MainScreen.route,
                    modifier = Modifier.padding(it),
                ) {
                    composable(route = Screens.MainScreen.route) {
                        Crossfade(targetState = v) { state ->
                            MainScreen(
                                weatherState = w,
                                venuesState = state,
                            )
                        }
                    }
                    composable(route = Screens.NotesScreen.route) {
                        NotesScreen()
                    }
                    composable(route = Screens.ProfileScreen.route) {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}

data class BottomNavigationBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
)
