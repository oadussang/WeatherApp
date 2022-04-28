package net.klonima.domain.entity

import net.klonima.domain.utils.StringUtils.Companion.EMPTY_STRING
import java.util.Date

data class WeatherDetailEntity (
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
)