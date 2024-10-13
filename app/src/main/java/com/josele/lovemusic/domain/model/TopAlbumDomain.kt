package com.josele.lovemusic.domain.model

import com.josele.lovemusic.data.remote.api.response.model.topalbums.Artist
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Image

data class TopAlbumDomain(
    val artist: Artist,
    val image: List<Image>,
    val mBid: String,
    val name: String,
    val playCount: Int,
    val url: String
)