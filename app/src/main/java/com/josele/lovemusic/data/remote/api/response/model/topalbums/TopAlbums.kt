package com.josele.lovemusic.data.remote.api.response.model.topalbums


import com.google.gson.annotations.SerializedName

data class TopAlbums(
    @SerializedName("album")
    val albumList: List<Album>,
    @SerializedName("@attr")
    val attr: Attr
)