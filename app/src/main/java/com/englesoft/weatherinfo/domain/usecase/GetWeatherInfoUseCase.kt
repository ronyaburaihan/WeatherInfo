package com.englesoft.weatherinfo.domain.usecase

import com.englesoft.weatherinfo.domain.model.WeatherInfo
import com.englesoft.weatherinfo.domain.repository.WeatherRepository
import javax.inject.Inject
import com.englesoft.weatherinfo.util.Result

class GetWeatherInfoUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Result<WeatherInfo> {
        return repository.getWeatherData(lat, lon)
    }
}