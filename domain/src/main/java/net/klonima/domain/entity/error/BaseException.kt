package net.klonima.domain.entity.error

open class BaseException(cause: Exception? = null) : Exception(cause) {
    data class ApiException(val statusCode: String) : BaseException()
    class NoInternetException(cause: Exception) : BaseException(cause)
    class NetworkResponseParseException : BaseException()
    class UnexpectedException(cause: Exception) : Exception(cause)
}