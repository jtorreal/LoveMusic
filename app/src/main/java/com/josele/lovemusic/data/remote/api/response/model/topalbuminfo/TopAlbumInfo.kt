package com.josele.lovemusic.data.remote.api.response.model.topalbuminfo


import com.google.gson.annotations.SerializedName

data class TopAlbumInfo(
    @SerializedName("album")
    val album: Album,
    @SerializedName("message")
    val message: String,
    @SerializedName("error")
    val error: Int
)