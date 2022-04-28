package net.klonima.data.repository

import net.klonima.data.extension.mapNetworkResponse
import net.klonima.data.network.WeatherApi
import net.klonima.domain.entity.LocationEntity
import net.klonima.domain.entity.WeatherDetailInformationEntity
import net.klonima.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getLocationByQuery(query: String): Result<List<LocationEntity>> {
        return runCatching { api.getLocationByQuery(query) }.mapNetworkResponse()
    }

    override suspend fun getWeatherByLocationWoeId(woeId: String): Result<WeatherDetailInformationEntity> {
        return runCatching { api.getWeatherByLocationWoeId(woeId) }.mapNetworkResponse()
    }
}