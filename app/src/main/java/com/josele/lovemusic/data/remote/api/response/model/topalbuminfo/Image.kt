package com.josele.lovemusic.data.remote.api.response.model.topalbuminfo


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("size")
    val size: String,
    @SerializedName("#text")
    val text: String
)