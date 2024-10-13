package com.josele.lovemusic.data.remote.network

import retrofit2.HttpException

fun getMessageException(e: HttpException): String {
    return when (e.code()) {
        400 -> ErrorMessages.BAD_REQUEST
        401 -> ErrorMessages.NOT_AUTHORIZED
        403 -> ErrorMessages.FORBIDDEN
        404 -> ErrorMessages.NOT_FOUND
        408 -> ErrorMessages.REQUEST_TIMEOUT
        500 -> ErrorMessages.SERVER_ERROR
        else -> {
            ErrorMessages.GENERAL_ERROR
        }
    }
}

object ErrorMessages {
    const val GENERAL_ERROR = "There has been an error. Please try again later."
    const val BAD_REQUEST = "Bad request"
    const val NOT_AUTHORIZED = "Not authorized"
    const val FORBIDDEN = "Fobidden"
    const val NOT_FOUND = "Not found"
    const val REQUEST_TIMEOUT = "Request timeout"
    const val SERVER_ERROR = "Internal server error"
}




