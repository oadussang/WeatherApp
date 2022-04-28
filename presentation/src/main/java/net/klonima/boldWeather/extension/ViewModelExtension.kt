package net.klonima.boldWeather.extension

import kotlinx.coroutines.CoroutineScope
import net.klonima.domain.utils.StringUtils.Companion.EMPTY_STRING

@JvmName("executeListResponse")
fun <T> CoroutineScope.execute(
    useCaseCall: Result<List<T>>,
    onSuccess: (List<T>) -> Unit,
    onFailure: (message: String)->Unit
) {
    kotlin.runCatching { useCaseCall }
        .onSuccess {
            if (it.isFailure) {
                onFailure.invoke(it.toString())
            } else {
                onSuccess.invoke(it.getOrDefault(listOf()))
            }
        }
        .onFailure {
            onFailure.invoke(it.localizedMessage ?: EMPTY_STRING)
        }
}

@JvmName("executeEntityResponse")
fun <T> CoroutineScope.execute(
    useCaseCall: Result<T>,
    onSuccess: (T?) -> Unit,
    onFailure: (message: String)->Unit
) {
    kotlin.runCatching { useCaseCall }
        .onSuccess {
            if (it.isFailure) {
                onFailure.invoke(it.toString())
            } else {
                onSuccess.invoke(it.getOrNull())
            }
        }
        .onFailure {
            onFailure.invoke(it.localizedMessage ?: EMPTY_STRING)
        }
}