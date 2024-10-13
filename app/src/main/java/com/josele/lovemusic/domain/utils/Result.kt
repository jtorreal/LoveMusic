package com.josele.lovemusic.domain.utils


sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable? = null, val message: String) : Result<Nothing>()
}