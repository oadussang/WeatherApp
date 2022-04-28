package net.klonima.boldWeather.extension

import java.util.Date
import java.util.Calendar

fun Date.parseHourAndMinute(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val hours = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)
    val timeSuffix = if (hours in 0..11) "AM" else "PM"
    return String.format("%02d:%02d %s", hours%12, minutes, timeSuffix)
}

fun Date.parseDayOfForecast(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return when (calendar.get(Calendar.DAY_OF_WEEK)) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> "Lost in Time :("
    }
}