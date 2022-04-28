package net.klonima.boldWeather.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap
import net.klonima.boldWeather.viewmodel.WeatherViewModel

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class ViewModelModule {

//    @Provides
//    fun provideWeatherViewModel(
//        getLocationSearchUseCase: GetLocationSearchUseCase,
//        getWeatherByLocationUseCase: GetWeatherByLocationUseCase
//    ) = WeatherViewModel(getLocationSearchUseCase, getWeatherByLocationUseCase)

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel
}