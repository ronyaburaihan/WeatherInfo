package com.englesoft.weatherinfo.data.repository

import com.englesoft.weatherinfo.data.mapper.toWeatherInfo
import com.englesoft.weatherinfo.data.remote.WeatherApi
import com.englesoft.weatherinfo.domain.model.WeatherInfo
import com.englesoft.weatherinfo.domain.repository.WeatherRepository
import com.englesoft.weatherinfo.util.Result
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, lon: Double): Result<WeatherInfo> {
        return try {
            Result.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = lon
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Result.Error(e.message ?: "An unknown error occurred.")
        }
    }
}