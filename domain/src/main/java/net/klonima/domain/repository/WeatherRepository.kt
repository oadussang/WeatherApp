package net.klonima.domain.repository

import net.klonima.domain.entity.LocationEntity
import net.klonima.domain.entity.WeatherDetailInformationEntity
import retrofit2.Response

interface WeatherRepository {
    suspend fun getLocationByQuery(query: String): Result<List<LocationEntity>>
    suspend fun getWeatherByLocationWoeId(woeId: String): Result<WeatherDetailInformationEntity>
}