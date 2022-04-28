package net.klonima.boldWeather.usecases

import net.klonima.data.repository.WeatherRepositoryImpl
import net.klonima.domain.entity.WeatherDetailInformationEntity
import net.klonima.domain.usecases.GetWeatherByLocationWoeIdUseCase
import javax.inject.Inject

class GetWeatherByLocationWoeIdUseCaseImpl @Inject constructor(
    private val repository: WeatherRepositoryImpl
): GetWeatherByLocationWoeIdUseCase {
    override suspend fun invoke(woeId: String): Result<WeatherDetailInformationEntity> {
        return repository.getWeatherByLocationWoeId(woeId)
    }
}