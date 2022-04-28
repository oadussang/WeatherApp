package net.klonima.data.extension

import net.klonima.data.entity.response.LocationResponseDTO
import net.klonima.data.entity.response.WeatherLocationResponseDTO
import net.klonima.data.entity.response.mapToDomain
import net.klonima.domain.entity.LocationEntity
import net.klonima.domain.entity.WeatherDetailInformationEntity

@JvmName("LocationMapNetworkResponse")
fun Result<List<LocationResponseDTO>>.mapNetworkResponse(): Result<List<LocationEntity>> {
    this.fold(
        onSuccess = { response ->
            return Result.success( response.map { entityDTO -> entityDTO.mapToDomain() })
        },
        onFailure = {
            return Result.failure(it)
        }
    )
}

@JvmName("WeatherDetailMapNetworkResponse")
fun Result<WeatherLocationResponseDTO>.mapNetworkResponse(): Result<WeatherDetailInformationEntity> {
    this.fold(
        onSuccess = { response ->
            return Result.success( response.mapToDomain() )
        },
        onFailure = {
            return Result.failure(it)
        }
    )
}

fun <T> Result<T>.resolve(
    onSuccess: (T) -> Unit,
    onFailure: (message: String)->Unit
) {
    this.onSuccess {
        if (this.isFailure) {
            onFailure.invoke(it.toString())
        } else {
            onSuccess.invoke(it)
        }
    }.onFailure {
        onFailure.invoke(it.message.toString())
    }
}