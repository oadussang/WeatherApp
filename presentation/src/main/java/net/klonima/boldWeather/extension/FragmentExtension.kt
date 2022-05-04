package net.klonima.boldWeather.extension

import androidx.fragment.app.Fragment
import net.klonima.boldWeather.ui.modal.GenericErrorModalFragment
import net.klonima.boldWeather.ui.modal.NoNetworkErrorModalFragment
import net.klonima.domain.entity.error.BaseException
import net.klonima.domain.utils.StringUtils.Companion.GENERIC_ERROR


fun Fragment.handleErrors(throwable: Throwable, otherErrors: ((Throwable) -> Unit)? = null) {
    when(throwable) {
        is BaseException.NoInternetException -> showNoInternetException(throwable.cause?.message)
        is BaseException.UnexpectedException,
        is BaseException.ApiException,
        is BaseException.NetworkResponseParseException -> showGenericUnexpectedException(throwable.cause?.message ?: GENERIC_ERROR)
        else -> otherErrors?.invoke(throwable)
    }

}

fun Fragment.showNoInternetException(errorMessage: String? = null) {
    this.activity?.let {
        NoNetworkErrorModalFragment(errorMessage).show(it.supportFragmentManager, "NETWORK_ERROR_MODAL_FRAGMENT")
    }
}

fun Fragment.showGenericUnexpectedException(errorMessage: String) {
    this.activity?.let {
        GenericErrorModalFragment(errorMessage).show(it.supportFragmentManager, "GENERIC_ERROR_MODAL_FRAGMENT")
    }
}
