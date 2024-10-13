package com.josele.lovemusic.data.remote.api.response.model


import com.google.gson.annotations.SerializedName

data class Topalbums(
    @SerializedName("album")
    val album: List<Album>,
    @SerializedName("@attr")
    val attr: Attr
)