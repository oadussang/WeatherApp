package net.klonima.boldWeather.entity

import net.klonima.domain.entity.WeatherDetailInformationEntity
import net.klonima.domain.utils.StringUtils
import java.util.Date

class WeatherDetailUiEntity (
    val sunRise: Date? = null,
    val sunSet: Date?  = null,
    val title: String? = StringUtils.EMPTY_STRING,
    val locationType: String? = StringUtils.EMPTY_STRING,
    val timezone: String? = StringUtils.EMPTY_STRING,
) {
    companion object {
        fun mapFromDomain(domainEntity: WeatherDetailInformationEntity): WeatherDetailUiEntity {
            return WeatherDetailUiEntity(
                sunRise = domainEntity.sunRise,
                sunSet = domainEntity.sunSet,
                title = domainEntity.title,
                locationType = domainEntity.locationType,
                timezone = domainEntity.timezone,
            )
        }
    }
}
