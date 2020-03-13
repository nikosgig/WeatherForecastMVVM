package com.nikosgig.weatherforecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.nikosgig.weatherforecastmvvm.data.db.CurrentWeatherDao
import com.nikosgig.weatherforecastmvvm.data.db.WeatherLocationDao
import com.nikosgig.weatherforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.nikosgig.weatherforecastmvvm.data.db.entity.WeatherLocation
import com.nikosgig.weatherforecastmvvm.data.network.WeatherNetworkDataSource
import com.nikosgig.weatherforecastmvvm.data.network.response.CurrentWeatherResponse
import com.nikosgig.weatherforecastmvvm.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadCurrentWeather.observeForever { newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out CurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData(metric)
            return@withContext currentWeatherDao.getWeather()
        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    private suspend fun initWeatherData(metric: Boolean) {
        val lastWeatherLocation = weatherLocationDao.getLocation().value

        //first time it is init or changed location
        if (lastWeatherLocation == null
            || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather(metric)
            return
        }

        if(isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime))
            fetchCurrentWeather(metric)
    }

    private suspend fun fetchCurrentWeather(metric: Boolean) {
        if(metric) weatherNetworkDataSource.fetchCurrentWeather(locationProvider.getPreferredLocationString(), "m")
        else weatherNetworkDataSource.fetchCurrentWeather(locationProvider.getPreferredLocationString(), "f")
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}