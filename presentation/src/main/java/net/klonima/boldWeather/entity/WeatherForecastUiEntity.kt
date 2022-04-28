package net.klonima.boldWeather.entity

import net.klonima.domain.entity.WeatherDetailInformationEntity
import net.klonima.domain.utils.StringUtils.Companion.EMPTY_STRING
import java.util.Date

data class WeatherForecastUiEntity (
    val id: String? = EMPTY_STRING,
    val weatherStateName: String? = EMPTY_STRING,
    val weatherStateAbbr: String? = EMPTY_STRING,
    val windDirectionCompass: String? = EMPTY_STRING,
    val created: Date? = null,
    val applicableDate: Date? = null,
    val minTemp: Float? = 0F,
    val maxTemp: Float? = 0F,
    val theTemp: Float? = 0F,
    val windSpeed: Float? = 0F,
    val windDirection:Float? = 0F,
    val airPressure: Float? = 0F,
    val humidity: Float? = 0F,
    val visibility: Float? = 0F,
    val predictability: Int? = 0
) {
    companion object {
        fun mapFromDomain(domainEntity: WeatherDetailInformationEntity): List<WeatherForecastUiEntity> {
            return domainEntity.consolidatedWeather?.map {
                WeatherForecastUiEntity(
                    id = it.id,
                    weatherStateName = it.weatherStateName,
                    weatherStateAbbr = it.weatherStateAbbr,
                    windDirectionCompass = it.windDirectionCompass,
                    created = it.created,
                    applicableDate = it.applicableDate,
                    minTemp = it.minTemp,
                    maxTemp = it.maxTemp,
                    theTemp = it.theTemp,
                    windSpeed = it.windSpeed,
                    windDirection = it.windDirection,
                    airPressure = it.airPressure,
                    humidity = it.humidity,
                    visibility = it.visibility,
                    predictability = it.predictability
                )
            } ?: listOf()
        }
    }
}