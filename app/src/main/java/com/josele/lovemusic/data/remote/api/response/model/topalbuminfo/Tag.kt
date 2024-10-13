package com.josele.lovemusic.data.remote.api.response.model.topalbuminfo


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)