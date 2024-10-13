package com.josele.lovemusic.domain.model



data class TopAlbumInfoDomain (
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playCount: String,
    val tags: Tags,
    val tracks: Tracks,
    val url: String,
    val wiki: Wiki
)