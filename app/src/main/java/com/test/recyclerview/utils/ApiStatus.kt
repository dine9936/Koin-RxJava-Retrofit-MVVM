package com.test.recyclerview.utils

sealed class ApiStatus<T : Any> {
    class Loading<T : Any> : ApiStatus<T>()
    class Success<T : Any>(val data: T) : ApiStatus<T>()
    class Error<T : Any>(val message: String? = "No Internet connection...") : ApiStatus<T>()
    class Exception<T : Any>(val e: Throwable) : ApiStatus<T>()
}