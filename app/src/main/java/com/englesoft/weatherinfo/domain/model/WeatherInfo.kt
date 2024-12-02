package com.englesoft.weatherinfo.domain.model

data class WeatherInfo(
    val time: String,
    val temperatureCelsius: Int,
    val temperatureFeelsLike: Int,
    val temperatureMax: Int,
    val temperatureMin: Int,
    val pressure: Int,
    val visibility: Int,
    val windSpeed: Double,
    val weatherTitle: String,
    val weatherIcon: String,
    val humidity: Int,
    val city: String,
    val country: String,
    val sunrise: String,
    val sunset: String
)
