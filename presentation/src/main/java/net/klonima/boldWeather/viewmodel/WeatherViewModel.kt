package net.klonima.boldWeather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.klonima.boldWeather.entity.WeatherDetailUiEntity
import net.klonima.boldWeather.entity.WeatherForecastUiEntity
import net.klonima.boldWeather.extension.execute
import net.klonima.domain.entity.LocationEntity
import net.klonima.domain.usecases.GetWeatherByLocationWoeIdUseCase
import net.klonima.domain.usecases.GetLocationByQueryUseCase
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherByLocationWoeIdUseCase: GetWeatherByLocationWoeIdUseCase,
    private val getLocationByQueryUseCase: GetLocationByQueryUseCase
) : ViewModel() {

    val locationList: MutableLiveData<Pair<String,List<LocationEntity>>> by lazy { MutableLiveData<Pair<String,List<LocationEntity>>>() }
    val exception: MutableLiveData<Throwable> by lazy { MutableLiveData<Throwable>() }

    val weatherDetails: MutableLiveData<WeatherDetailUiEntity> by lazy { MutableLiveData<WeatherDetailUiEntity>() }
    val weatherForecast: MutableLiveData<List<WeatherForecastUiEntity>> by lazy { MutableLiveData<List<WeatherForecastUiEntity>>() }

    fun getLocationBySearch(query: String) {
        viewModelScope.launch {
            execute(
                useCaseCall = getLocationByQueryUseCase.invoke(query),
                onSuccess = { responseList: List<LocationEntity> -> locationList.value = Pair(query,responseList) },
                onFailure = { exception.value = it }
            )
        }
    }

    fun getWeatherByLocationWoeId(woeId: String) {
        viewModelScope.launch {
            execute(
                useCaseCall = getWeatherByLocationWoeIdUseCase.invoke(woeId),
                onSuccess = {
                    it?.let {
                        weatherDetails.value = WeatherDetailUiEntity.mapFromDomain(it)
                        weatherForecast.value = WeatherForecastUiEntity.mapFromDomain(it)
                    }
                },
                onFailure = { exception.value = it }
            )
        }
    }
}
