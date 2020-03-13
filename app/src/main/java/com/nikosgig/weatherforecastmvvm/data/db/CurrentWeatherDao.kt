package com.nikosgig.weatherforecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikosgig.weatherforecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.nikosgig.weatherforecastmvvm.data.db.entity.CurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    //update if exists or insert if not
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("SELECT * FROM current_weather WHERE id= $CURRENT_WEATHER_ID")
    fun getWeather(): LiveData<CurrentWeatherEntry>
}