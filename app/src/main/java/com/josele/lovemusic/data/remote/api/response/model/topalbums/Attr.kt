package com.josele.lovemusic.data.remote.api.response.model.topalbums


import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("artist")
    val artist: String,
    @SerializedName("page")
    val page: String,
    @SerializedName("perPage")
    val perPage: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("totalPages")
    val totalPages: String
)