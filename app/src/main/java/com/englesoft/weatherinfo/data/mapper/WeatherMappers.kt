package com.englesoft.weatherinfo.data.mapper

import com.englesoft.weatherinfo.data.model.WeatherDataDto
import com.englesoft.weatherinfo.domain.model.WeatherInfo
import com.englesoft.weatherinfo.data.util.kelvinToCelsius
import com.englesoft.weatherinfo.data.util.unixTimestampToDateTimeString
import com.englesoft.weatherinfo.data.util.unixTimestampToTimeString

fun WeatherDataDto.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        time = dt.unixTimestampToDateTimeString(),
        temperatureCelsius = main.temp.kelvinToCelsius(),
        temperatureFeelsLike = main.feels_like.kelvinToCelsius(),
        temperatureMax = main.temp_max.kelvinToCelsius(),
        temperatureMin = main.temp_min.kelvinToCelsius(),
        pressure = main.pressure,
        visibility = visibility,
        windSpeed = wind.speed,
        humidity = main.humidity,
        weatherTitle = weather.first().main,
        weatherIcon = weather.first().icon,
        city = name,
        country = sys.country,
        sunrise = sys.sunrise.unixTimestampToTimeString(),
        sunset = sys.sunset.unixTimestampToTimeString()
    )
}