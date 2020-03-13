package com.nikosgig.weatherforecastmvvm.data.provider

import com.nikosgig.weatherforecastmvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitProvider(): UnitSystem
}