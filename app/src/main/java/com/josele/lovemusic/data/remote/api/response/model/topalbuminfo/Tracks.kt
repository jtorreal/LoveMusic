package com.josele.lovemusic.data.remote.api.response.model.topalbuminfo


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("track")
    val track: List<Track>
)