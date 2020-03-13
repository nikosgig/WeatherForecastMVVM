package com.nikosgig.weatherforecastmvvm.data.db.unitLocalized

import androidx.room.ColumnInfo


data class CurrentWeatherEntry(
    @ColumnInfo(name = "temperature")
    override val temperature: Double,
    @ColumnInfo(name = "weather_descriptions")
    override val conditionText: List<String>,
    @ColumnInfo(name = "weather_icons")
    override val conditionIconUrl: List<String>,
    @ColumnInfo(name = "wind_speed")
    override val windSpeed: Double,
    @ColumnInfo(name = "wind_dir")
    override val windDirection: String,
    @ColumnInfo(name = "precip")
    override val precipitationVolume: Double,
    @ColumnInfo(name = "feelslike")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "visibility")
    override val visibilityDistance: Double
) : UnitSpecificCurrentWeatherEntry