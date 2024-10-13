package com.josele.lovemusic.data.remote.api.response.model.topalbuminfo


import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("tag")
    val tag: List<Tag>
)