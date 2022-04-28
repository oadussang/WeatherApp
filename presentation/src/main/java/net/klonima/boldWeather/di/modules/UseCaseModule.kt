package net.klonima.boldWeather.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.klonima.boldWeather.usecases.GetLocationByQueryUseCaseImpl
import net.klonima.boldWeather.usecases.GetWeatherByLocationWoeIdUseCaseImpl
import net.klonima.domain.usecases.GetWeatherByLocationWoeIdUseCase
import net.klonima.domain.usecases.GetLocationByQueryUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun providesGetWeatherByLocationWoeIdUseCase(impl: GetWeatherByLocationWoeIdUseCaseImpl) : GetWeatherByLocationWoeIdUseCase

    @Binds
    abstract fun providesGetLocationByQueryUseCase(impl: GetLocationByQueryUseCaseImpl) : GetLocationByQueryUseCase
}