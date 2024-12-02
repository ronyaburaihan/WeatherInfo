package com.englesoft.weatherinfo.di

import com.englesoft.weatherinfo.data.repository.WeatherRepositoryImpl
import com.englesoft.weatherinfo.data.repository.ZilaRepositoryImpl
import com.englesoft.weatherinfo.data.util.ZilaAssetsReader
import com.englesoft.weatherinfo.domain.repository.WeatherRepository
import com.englesoft.weatherinfo.domain.repository.ZilaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}