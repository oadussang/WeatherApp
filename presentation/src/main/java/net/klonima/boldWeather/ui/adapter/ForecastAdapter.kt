package net.klonima.boldWeather.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color.*
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.recyclerview.widget.RecyclerView
import net.klonima.boldWeather.R
import net.klonima.boldWeather.entity.WeatherForecastUiEntity
import net.klonima.boldWeather.extension.parseDayOfForecast
import net.klonima.domain.entity.LocationEntity

class ForecastAdapter(private var dataset: List<WeatherForecastUiEntity>) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    fun setDataSet(dataset: List<WeatherForecastUiEntity>) {
        this.dataset = dataset
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayOfForecast: TextView = view.findViewById(R.id.tv_day_of_the_week)
        val weatherStatus: TextView = view.findViewById(R.id.tv_weather_forecast)
        val temp: TextView = view.findViewById(R.id.tv_temp_forecast)
        val wind: TextView = view.findViewById(R.id.tv_wind_forecast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_weather_forecast, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataset[position].let {
            holder.dayOfForecast.text = it.applicableDate?.parseDayOfForecast()
            holder.weatherStatus.text = String.format("%s | %.1f%%", it.weatherStateName, it.predictability?.toFloat())
            val tempText = buildSpannedString {
                inSpans(ForegroundColorSpan(BLACK)) { append(String.format("%sº | ",it.theTemp?.toInt())) }
                inSpans(ForegroundColorSpan(RED)) { append(String.format("%sº",it.maxTemp?.toInt())) }
                inSpans(ForegroundColorSpan(BLACK)) { append(" | ") }
                inSpans(ForegroundColorSpan(BLUE)) { append(String.format("%sº",it.minTemp?.toInt())) }
            }
            holder.temp.text = tempText
            holder.wind.text = String.format("%.2fmph | %s", it.windSpeed, it.windDirectionCompass)
        }
    }

    override fun getItemCount() = dataset.size
}