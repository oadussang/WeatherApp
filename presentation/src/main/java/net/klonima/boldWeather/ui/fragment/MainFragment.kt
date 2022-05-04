package net.klonima.boldWeather.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.klonima.boldWeather.databinding.FragmentMainBinding
import net.klonima.boldWeather.extension.handleErrors
import net.klonima.boldWeather.ui.adapter.LocationResultAdapter
import net.klonima.domain.utils.StringUtils.Companion.EMPTY_STRING
import net.klonima.domain.utils.StringUtils.Companion.QUERY_FILLER_STRING
import net.klonima.boldWeather.viewmodel.WeatherViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object { fun newInstance() = MainFragment() }

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: LocationResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(LayoutInflater.from(this.context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tietLocationSearch.addTextChangedListener {
            viewModel.getLocationBySearch(it.toString().ifEmpty { QUERY_FILLER_STRING })
        }
        binding.rvResultList.layoutManager = LinearLayoutManager(context)
        adapter = LocationResultAdapter(Pair(EMPTY_STRING,listOf()))
        binding.rvResultList.adapter = adapter
        addListeners()
    }

    private fun addListeners() {
        viewModel.locationList.observe(viewLifecycleOwner) { pair ->
            if (pair.second.isEmpty()) {
                adapter.setDataSet(Pair(EMPTY_STRING,listOf()))
            }
            adapter.setDataSet(dataset = Pair(pair.first, pair.second.map { it }.filter{ it.title?.isNotBlank() ?: false }))
        }
        viewModel.exception.observe(viewLifecycleOwner) { handleErrors(it) }
        adapter.setItemClickListener { woeId -> woeId?.let { navigateToWeatherDetail(it) }}
    }

    private fun navigateToWeatherDetail(woeId: String) {
        val action = MainFragmentDirections.actionMainScreenToBlankFragment()
        action.argWoeid = woeId
        findNavController().navigate(action)
    }
}