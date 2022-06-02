package com.elnemr.movieflix.domain.result

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(response: T) : NetworkResult<T>(response)
    class ServerError<T>(message: String?, response: T? = null) :
        NetworkResult<T>(response, message)

    class Loading<T> : NetworkResult<T>()
    class Empty<T> : NetworkResult<T>()
    class NetworkError<T>(message: String?, response: T? = null) :
        NetworkResult<T>(response, message)
}