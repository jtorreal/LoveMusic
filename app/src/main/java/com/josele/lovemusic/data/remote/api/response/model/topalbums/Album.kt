package com.josele.lovemusic.data.remote.api.response.model.topalbums


import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("mbid")
    val mBid: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("playcount")
    val playcount: Int?,
    @SerializedName("url")
    val url: String?
)