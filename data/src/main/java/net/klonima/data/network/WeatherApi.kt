package net.klonima.data.network

import net.klonima.data.entity.response.LocationResponseDTO
import net.klonima.data.entity.response.WeatherLocationResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("location/search/")
    suspend fun getLocationByQuery(@Query("query") search: String) : List<LocationResponseDTO>

    @GET("location/{woeId}/")
    suspend fun getWeatherByLocationWoeId(@Path("woeId") woeId: String): WeatherLocationResponseDTO
}