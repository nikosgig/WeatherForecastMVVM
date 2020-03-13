package com.nikosgig.weatherforecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.nikosgig.weatherforecastmvvm.data.provider.UnitProvider
import com.nikosgig.weatherforecastmvvm.data.repository.ForecastRepository
import com.nikosgig.weatherforecastmvvm.internal.UnitSystem
import com.nikosgig.weatherforecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitProvider()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}
