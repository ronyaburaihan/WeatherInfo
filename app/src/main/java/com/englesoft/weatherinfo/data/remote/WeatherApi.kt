package com.englesoft.weatherinfo.data.remote

import com.englesoft.weatherinfo.data.model.WeatherDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") long: Double
    ): WeatherDataDto
}