package net.klonima.domain.entity

data class LocationEntity (
    val title: String? = null,
    val location: String? = null,
    val woeid: String? = null,
    val lat: Float? = 0F,
    val long: Float? = 0F
)