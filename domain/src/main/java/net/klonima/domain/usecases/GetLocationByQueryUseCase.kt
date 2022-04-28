package net.klonima.domain.usecases

import net.klonima.domain.entity.LocationEntity
import retrofit2.Response

interface GetLocationByQueryUseCase {
    suspend fun invoke(query: String): Result<List<LocationEntity>>
}