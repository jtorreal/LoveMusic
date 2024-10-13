package com.josele.lovemusic.data.remote.api.response.model


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("error")
    val error: Int,
    @SerializedName("message")
    val message: String
)