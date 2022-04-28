package net.klonima.data.entity.response

data class WeatherInformationSourceDTO(
    private val title: String,
    private val slug: String,
    private val url: String,
    private val crawl_rate: Int
)