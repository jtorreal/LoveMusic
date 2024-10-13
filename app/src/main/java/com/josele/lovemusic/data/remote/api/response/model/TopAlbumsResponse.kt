package com.josele.lovemusic.data.remote.api.response.model


import com.google.gson.annotations.SerializedName
import com.josele.lovemusic.data.remote.api.response.model.topalbums.TopAlbums

data class TopAlbumsResponse(
    @SerializedName("topalbums")
    val topalbums: TopAlbums,
    @SerializedName("message")
    val message: String,
    @SerializedName("error")
    val error: Int
)

