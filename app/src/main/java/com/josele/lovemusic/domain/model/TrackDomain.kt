package com.josele.lovemusic.domain.model

import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Artist
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Attr

data class TrackDomain(
    val artist: Artist,
    val attr: Attr,
    val duration: Int,
    val name: String
)