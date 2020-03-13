package com.nikosgig.weatherforecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.nikosgig.weatherforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.nikosgig.weatherforecastmvvm.data.db.entity.WeatherLocation


data class CurrentWeatherResponse(
    @SerializedName("current") val currentWeatherEntry: CurrentWeatherEntry,
    val location: WeatherLocation,
    val request: Request
)