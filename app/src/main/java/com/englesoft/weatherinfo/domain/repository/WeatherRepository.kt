package com.englesoft.weatherinfo.domain.repository

import com.englesoft.weatherinfo.domain.model.WeatherInfo
import com.englesoft.weatherinfo.util.Result

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lon: Double): Result<WeatherInfo>
}