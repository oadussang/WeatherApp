package net.klonima.boldWeather.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import net.klonima.boldWeather.databinding.FragmentBlankBinding
import net.klonima.boldWeather.extension.handleErrors
import net.klonima.boldWeather.extension.parseHourAndMinute
import net.klonima.boldWeather.ui.adapter.ForecastAdapter
import net.klonima.boldWeather.viewmodel.WeatherViewModel



@AndroidEntryPoint
class BlankFragment : Fragment() {

    private var woeId: String? = null
    private lateinit var binding: FragmentBlankBinding
    private lateinit var adapter: ForecastAdapter

    private val viewModel: WeatherViewModel by viewModels()

    companion object {
        private const val ARG_WOEID = "arg_woeid"
        @JvmStatic
        fun newInstance(woeId: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_WOEID, woeId)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            woeId = it.getString(ARG_WOEID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(LayoutInflater.from(this.context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        initViewModel()
        adapter = ForecastAdapter(listOf())
        binding.rvForecast.layoutManager = when(this.activity?.resources?.configuration?.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            Configuration.ORIENTATION_PORTRAIT -> LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            else -> LinearLayoutManager(context)
        }
        binding.rvForecast.adapter = adapter
        setListeners()
    }

    private fun setClickListeners() {
        binding.ivBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initViewModel() {
        woeId?.let { viewModel.getWeatherByLocationWoeId(it) }
    }

    private fun setListeners() {
        viewModel.weatherDetails.observe(viewLifecycleOwner) {
            binding.tvCityName.text = it.title
            binding.tvLocationType.text = String.format("( %s ) - <%s>",it.locationType,it.timezone)
            binding.tvSunrise.text = String.format("Sunrise: %s",it.sunRise?.parseHourAndMinute())
            binding.tvSunset.text = String.format("Sunset: %s", it.sunSet?.parseHourAndMinute())
            binding.incLoading.lavLoading.isVisible = false
        }
        viewModel.weatherForecast.observe(viewLifecycleOwner) {
            // weather
            binding.incWeatherConditions.tvWeatherStatus.text = it.first().weatherStateName
            binding.incWeatherConditions.tvPredictability.text = String.format("Predictability: %.2f%%", it.first().predictability?.toFloat())
            binding.incWeatherConditions.tvVisibility.text = String.format("Visibility: %.2f miles", it.first().visibility)

            // temp
            binding.incWeatherTemp.tvCurrentTemp.text = String.format("%sº",it.first().theTemp?.toInt())
            binding.incWeatherTemp.tvMaxTemp.text = String.format("%sº",it.first().maxTemp?.toInt())
            binding.incWeatherTemp.tvMinTemp.text = String.format("%sº",it.first().minTemp?.toInt())

            // wind
            binding.incWeatherWind.tvWindSpeed.text = String.format("Speed: %.2f mph | %s", it.first().windSpeed, it.first().windDirectionCompass)
            binding.incWeatherWind.tvPressure.text = String.format("Pressure: %s mbar", it.first().airPressure)
            binding.incWeatherWind.tvHumidity.text = String.format("Humidity: %.2f%%", it.first().humidity)

            // forecast
            adapter.setDataSet(it)
        }
        viewModel.exception.observe(viewLifecycleOwner) { handleErrors(it) }
    }
}