package net.klonima.boldWeather.extension

import kotlinx.coroutines.CoroutineScope
import net.klonima.domain.utils.StringUtils.Companion.EMPTY_STRING

@JvmName("executeListResponse")
fun <T> CoroutineScope.execute(
    useCaseCall: Result<List<T>>,
    onSuccess: (List<T>) -> Unit,
    onFailure: (throwable: Throwable?)->Unit
) {
    kotlin.runCatching { useCaseCall }
        .onSuccess {
            if (it.isFailure) {
                onFailure.invoke(it.exceptionOrNull())
            } else {
                onSuccess.invoke(it.getOrDefault(listOf()))
            }
        }
        .onFailure {
            onFailure.invoke(it)
        }
}

@JvmName("executeEntityResponse")
fun <T> CoroutineScope.execute(
    useCaseCall: Result<T>,
    onSuccess: (T?) -> Unit,
    onFailure: (throwable: Throwable?)->Unit
) {
    kotlin.runCatching { useCaseCall }
        .onSuccess {
            if (it.isFailure) {
                onFailure.invoke(it.exceptionOrNull())
            } else {
                onSuccess.invoke(it.getOrNull())
            }
        }
        .onFailure {
            onFailure.invoke(it)
        }
}