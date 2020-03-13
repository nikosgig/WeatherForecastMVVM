package com.nikosgig.weatherforecastmvvm.data.network

import androidx.lifecycle.LiveData
import com.nikosgig.weatherforecastmvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        unit: String
    )
}