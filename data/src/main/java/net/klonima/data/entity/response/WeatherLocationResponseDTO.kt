package net.klonima.data.entity.response

import com.google.gson.annotations.SerializedName
import net.klonima.domain.entity.WeatherDetailInformationEntity
import java.util.*

data class WeatherLocationResponseDTO (
    @SerializedName("consolidated_weather") private val consolidatedWeather: List<WeatherDetailDTO>,
    private val time: Date,
    @SerializedName("sun_rise") private val sunRise: Date,
    @SerializedName("sun_set") private val sunSet: Date,
    @SerializedName("timezone_name") private val timezoneName: String,
    private val parent: LocationResponseDTO,
    private val sources: List<WeatherInformationSourceDTO>,
    private val title: String,
    @SerializedName("location_type") private val locationType: String,
    private val woeid: String,
    @SerializedName("latt_long") private val latLong: String,
    private val timezone: String
) {
    companion object {
        val mapToDomain : (WeatherLocationResponseDTO) -> WeatherDetailInformationEntity = { dto ->
            WeatherDetailInformationEntity(
                sunRise = dto.sunRise,
                sunSet = dto.sunSet,
                title = dto.title,
                locationType = dto.locationType,
                timezone = dto.timezone,
                consolidatedWeather = dto.consolidatedWeather.map { it.mapToDomain() }
            )
        }
    }
}

fun WeatherLocationResponseDTO.mapToDomain(): WeatherDetailInformationEntity {
    return WeatherLocationResponseDTO.mapToDomain(this)
}