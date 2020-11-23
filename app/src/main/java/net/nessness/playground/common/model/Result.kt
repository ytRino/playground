package net.nessness.playground.common.model

import java.lang.Exception

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Error<T>(exception: Exception) : Result<T>()
}
