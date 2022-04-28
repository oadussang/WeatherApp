package net.klonima.data.entity.response

import com.google.gson.annotations.SerializedName
import net.klonima.domain.entity.LocationEntity

data class LocationResponseDTO (
    private val title: String,
    @SerializedName("location_type") private val location: String,
    private val woeid: String,
    @SerializedName("latt_long") val latLong: String
) {
    companion object {
        val mapToDomain : (LocationResponseDTO) -> LocationEntity = { dto ->
            LocationEntity(
                title = dto.title,
                location = dto.location,
                woeid = dto.woeid,
                lat = dto.latLong.split(",").first().toFloat(),
                long = dto.latLong.split(",").last().toFloat()
            )
        }
    }
}

fun LocationResponseDTO.mapToDomain(): LocationEntity {
    return LocationResponseDTO.mapToDomain(this)
}