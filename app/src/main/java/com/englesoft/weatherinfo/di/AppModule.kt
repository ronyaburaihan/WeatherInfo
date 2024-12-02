package com.englesoft.weatherinfo.di

import android.app.Application
import android.content.Context
import com.englesoft.weatherinfo.BuildConfig
import com.englesoft.weatherinfo.data.remote.WeatherApi
import com.englesoft.weatherinfo.data.repository.ZilaRepositoryImpl
import com.englesoft.weatherinfo.data.util.ZilaAssetsReader
import com.englesoft.weatherinfo.domain.repository.ZilaRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val url = originalRequest.url.newBuilder()
                    .addQueryParameter("appid", BuildConfig.APP_ID)
                    .build()
                val newRequest = originalRequest.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideZilaAssetsReader(@ApplicationContext context: Context): ZilaAssetsReader {
        return ZilaAssetsReader(context)
    }

    @Provides
    @Singleton
    fun provideZilaRepository(
        zilaAssetsReader: ZilaAssetsReader
    ): ZilaRepository {
        return ZilaRepositoryImpl(zilaAssetsReader)
    }
}