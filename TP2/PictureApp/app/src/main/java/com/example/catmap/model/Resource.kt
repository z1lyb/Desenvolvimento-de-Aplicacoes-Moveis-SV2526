package com.example.catmap.model

/**
 * Generic wrapper for API call results.
 *
 * Used by [com.example.catmap.repository.ImageRepository] to communicate
 * success, failure, or in-progress state to the ViewModel layer.
 *
 * @param T the type of data on success.
 */
sealed class Resource<out T> {

    /** The API call succeeded and [data] holds the result. */
    data class Success<out T>(val data: T) : Resource<T>()

    /** The API call failed; [message] describes the error. */
    data class Error(val message: String) : Resource<Nothing>()

    /** A network call is in progress. [progress] holds the percentage (0-100). */
    data class Loading(val progress: Int = 0) : Resource<Nothing>()
}
