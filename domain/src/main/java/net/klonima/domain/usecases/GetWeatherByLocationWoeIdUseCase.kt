package net.klonima.domain.usecases

import net.klonima.domain.entity.WeatherDetailInformationEntity

interface GetWeatherByLocationWoeIdUseCase {
    suspend fun invoke(woeId: String): Result<WeatherDetailInformationEntity>
}