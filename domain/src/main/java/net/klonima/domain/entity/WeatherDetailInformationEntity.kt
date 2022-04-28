package net.klonima.domain.entity

import net.klonima.domain.utils.StringUtils.Companion.EMPTY_STRING
import java.util.*

data class WeatherDetailInformationEntity(
    val sunRise: Date? = null,
    val sunSet: Date?  = null,
    val title: String? = EMPTY_STRING,
    val locationType: String? = EMPTY_STRING,
    val timezone: String? = EMPTY_STRING,
    val consolidatedWeather: List<WeatherDetailEntity>? = listOf()
)