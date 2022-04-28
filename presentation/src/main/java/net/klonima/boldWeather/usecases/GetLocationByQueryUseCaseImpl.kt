package net.klonima.boldWeather.usecases

import net.klonima.domain.entity.LocationEntity
import net.klonima.domain.repository.WeatherRepository
import net.klonima.domain.usecases.GetLocationByQueryUseCase
import javax.inject.Inject

class GetLocationByQueryUseCaseImpl @Inject constructor(
    private val repository: WeatherRepository
) : GetLocationByQueryUseCase {
    override suspend fun invoke(query: String): Result<List<LocationEntity>> {
        return repository.getLocationByQuery(query)
    }

}