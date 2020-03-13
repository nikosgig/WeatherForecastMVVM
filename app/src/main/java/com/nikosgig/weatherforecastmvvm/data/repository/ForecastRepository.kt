package com.nikosgig.weatherforecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.nikosgig.weatherforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.nikosgig.weatherforecastmvvm.data.db.entity.WeatherLocation

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out CurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>

}