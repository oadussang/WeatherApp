package net.klonima.data.extension

import net.klonima.data.entity.response.LocationResponseDTO
import net.klonima.data.entity.response.WeatherLocationResponseDTO
import net.klonima.data.entity.response.mapToDomain
import net.klonima.domain.entity.LocationEntity
import net.klonima.domain.entity.WeatherDetailInformationEntity
import net.klonima.domain.entity.error.BaseException
import net.klonima.domain.utils.StringUtils.Companion.EMPTY_STRING
import java.net.UnknownHostException

@JvmName("LocationMapNetworkResponse")
fun Result<List<LocationResponseDTO>>.mapNetworkResponse(): Result<List<LocationEntity>> {
    this.fold(
        onSuccess = { response ->
            return Result.success( response.map { entityDTO -> entityDTO.mapToDomain() })
        },
        onFailure = {
            val exception = when(it) {
                is UnknownHostException -> BaseException.NoInternetException(Exception(it.message))
                else -> BaseException.UnexpectedException(cause = Exception(it.message ?: EMPTY_STRING))
            }
            return Result.failure(exception)
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