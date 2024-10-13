package com.josele.lovemusic.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object TopAlbumList

@Serializable
data class TopAlbumInfo(val albumName: String)
