package net.klonima.boldWeather.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color.RED
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import net.klonima.boldWeather.R
import net.klonima.domain.entity.LocationEntity

class LocationResultAdapter(private var dataset: Pair<String, List<LocationEntity>>) : RecyclerView.Adapter<LocationResultAdapter.ViewHolder>() {

    private var onClick: ((woeid: String?) -> Unit)? = null

    fun setDataSet(dataset: Pair<String, List<LocationEntity>>) {
        this.dataset = dataset
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tv_title)
        val locationType: TextView = view.findViewById(R.id.tv_location_type)
        val container: ConstraintLayout = view.findViewById(R.id.cl_text_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_location_result, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataset.second[position].title?.let { title ->
            val spannable = SpannableStringBuilder(title)
            val indexSubstring = title.indexOf(string = dataset.first, ignoreCase = true)
            if (indexSubstring != -1)
                spannable.setSpan(
                    ForegroundColorSpan(RED),
                    indexSubstring,
                    indexSubstring + dataset.first.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
            holder.title.text = spannable
            holder.locationType.text = " (${dataset.second[position].location})"
        }

        holder.container.setOnClickListener { onClick?.invoke(dataset.second[position].woeid) }
    }

    override fun getItemCount() = dataset.second.size

    fun setItemClickListener(onClick: (woeid: String?) -> Unit) {
        this.onClick = onClick
    }
}