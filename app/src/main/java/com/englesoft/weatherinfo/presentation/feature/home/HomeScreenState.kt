package com.englesoft.weatherinfo.presentation.feature.home

import com.englesoft.weatherinfo.domain.model.WeatherInfo

data class HomeScreenState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)