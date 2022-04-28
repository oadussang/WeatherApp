package net.klonima.boldWeather.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.klonima.data.repository.WeatherRepositoryImpl
import net.klonima.domain.repository.WeatherRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun  provideWeatherRepository(impl: WeatherRepositoryImpl) : WeatherRepository
}