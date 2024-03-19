package com.yaabelozerov.lifestylehub.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yaabelozerov.lifestylehub.weather.presentation.WeatherCardViewModel
import com.yaabelozerov.lifestylehub.weather.presentation.WeatherState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherCardViewModel: WeatherCardViewModel by viewModels()

        setContent {
            MainScreen(weatherCardViewModel)
        }
    }
}