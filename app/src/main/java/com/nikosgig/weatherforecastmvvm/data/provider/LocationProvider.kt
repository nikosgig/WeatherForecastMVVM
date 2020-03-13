package com.nikosgig.weatherforecastmvvm.data.provider

import com.nikosgig.weatherforecastmvvm.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}