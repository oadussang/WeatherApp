package net.klonima.boldWeather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*

import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import net.klonima.boldWeather.extension.execute
import net.klonima.domain.entity.LocationEntity
import net.klonima.domain.entity.WeatherDetailInformationEntity
import net.klonima.domain.usecases.GetLocationByQueryUseCase
import net.klonima.domain.usecases.GetWeatherByLocationWoeIdUseCase

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class WeatherViewModelTest : TestCase() {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: WeatherViewModel
    private lateinit var weatherByLocationUseCaseMock: GetWeatherByLocationWoeIdUseCase
    private lateinit var locationByQueryUseCaseMock: GetLocationByQueryUseCase

    private val mainThreadSurrogate = newSingleThreadContext("UI/Main Test Thread")

    @Before
    public override fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        weatherByLocationUseCaseMock = mock(GetWeatherByLocationWoeIdUseCase::class.java)
        locationByQueryUseCaseMock = mock(GetLocationByQueryUseCase::class.java)
        sut = WeatherViewModel(weatherByLocationUseCaseMock, locationByQueryUseCaseMock)
    }

    @After
    public override fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun getLocationList() {
    }

    @Test
    fun getMessage() {
    }

    @Test
    fun getWeatherDetails() {
    }

    @Test
    fun getWeatherForecast() {
    }

    @Test
    fun weatherVM_getLocationBySearch_ReturnValidLocationList()  = runBlockingTest {
        // given
        val locationList = Result.success(listOf<LocationEntity>())
        val validQuery = "new "
        `when`(locationByQueryUseCaseMock.invoke(validQuery)).thenReturn(locationList)
        // when
        sut.getLocationBySearch(validQuery)
        // then
        verify(locationByQueryUseCaseMock).invoke(validQuery)
//        assertEquals(validQuery, sut.locationList.value?.first)
//        assertEquals(locationList, sut.locationList.value?.second)
    }

    @Test
    fun getWeatherByLocationWoeId() {
    }
}