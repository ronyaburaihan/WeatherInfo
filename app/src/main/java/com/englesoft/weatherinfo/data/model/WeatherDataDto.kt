package com.englesoft.weatherinfo.data.model

data class WeatherDataDto(
    val coord: CoordDto,
    val dt: Int,
    val id: Int,
    val main: MainWeatherDto,
    val name: String,
    val sys: SunriseInfoDto,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherTypeDto>,
    val wind: WindDto
)