package com.josele.lovemusic.domain.model


data class Track(
    val artist: Artist,
    val attr: Attr,
    val duration: Int,
    val name: String,
    val streamable: Streamable,
    val url: String
)