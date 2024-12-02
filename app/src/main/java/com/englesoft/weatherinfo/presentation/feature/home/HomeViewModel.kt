package com.englesoft.weatherinfo.presentation.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.englesoft.weatherinfo.domain.model.ZilaInfo
import com.englesoft.weatherinfo.domain.usecase.GetWeatherInfoUseCase
import com.englesoft.weatherinfo.domain.location.LocationTracker
import com.englesoft.weatherinfo.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    fun getWeatherBasedOnCurrentLocation() {
        viewModelScope.launch {
            val location = locationTracker.getCurrentLocation()

            if (location == null) {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
                return@launch
            }
            loadWeatherInfo(location.latitude, location.longitude)
        }
    }

    private suspend fun loadWeatherInfo(lat: Double, lon: Double) {
        state = state.copy(
            isLoading = true,
            error = null
        )

        val result = getWeatherInfoUseCase(lat, lon)

        state = when (result) {
            is Result.Success -> state.copy(
                weatherInfo = result.data,
                isLoading = false,
                error = null
            )

            else -> state.copy(
                weatherInfo = null,
                isLoading = false,
                error = result.message ?: "An unexpected error occurred."
            )
        }
    }

    fun updateWeatherForSelectedZila(zila: ZilaInfo) {
        viewModelScope.launch {
            loadWeatherInfo(zila.lat, zila.lon)
        }
    }
}