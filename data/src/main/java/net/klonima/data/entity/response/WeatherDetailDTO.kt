package net.klonima.data.entity.response

import com.google.gson.annotations.SerializedName
import net.klonima.domain.entity.WeatherDetailEntity
import java.util.Date

data class WeatherDetailDTO (
    private val id: String,
    @SerializedName("weather_state_name") private val weatherStateName: String,
    @SerializedName("weather_state_abbr") private val weatherStateAbbr: String,
    @SerializedName("wind_direction_compass") private val windDirectionCompass: String,
    private val created: Date,
    @SerializedName("applicable_date") private val applicableDate: Date,
    @SerializedName("min_temp") private val minTemp: Float,
    @SerializedName("max_temp") private val maxTemp: Float,
    @SerializedName("the_temp") private val theTemp: Float,
    @SerializedName("wind_speed") private val windSpeed: Float,
    @SerializedName("wind_direction") private val windDirection:Float,
    @SerializedName("air_pressure") private val airPressure: Float,
    private val humidity: Float,
    private val visibility: Float,
    private val predictability: Int
) {
    companion object {
        val mapToDomain : (WeatherDetailDTO) -> WeatherDetailEntity = { dto ->
            WeatherDetailEntity(
                id = dto.id,
                weatherStateName = dto.weatherStateName,
                weatherStateAbbr = dto.weatherStateAbbr,
                windDirectionCompass = dto.windDirectionCompass,
                created = dto.created,
                applicableDate = dto.applicableDate,
                minTemp = dto.minTemp,
                maxTemp = dto.maxTemp,
                theTemp = dto.theTemp,
                windSpeed = dto.windSpeed,
                windDirection = dto.windDirection,
                airPressure = dto.airPressure,
                humidity = dto.humidity,
                visibility = dto.visibility,
                predictability = dto.predictability
            )
        }
    }
}

fun WeatherDetailDTO.mapToDomain(): WeatherDetailEntity {
    return WeatherDetailDTO.mapToDomain(this)
}