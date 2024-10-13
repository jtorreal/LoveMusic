package com.josele.lovemusic.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Tags
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Tracks
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Wiki
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Artist
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Image

@Entity(tableName = "albumList")
data class TopAlbumListRoom (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val artist: Artist,
    val image: List<Image>,
    val mBid: String,
    val name: String,
    val playCount: Int,
    val url: String
)

@Entity(tableName = "albumInfo")
data class TopAlbumInfoRoom (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val artist: String,
    val image: List<Image>,
    val listeners: String,
    val mBid: String,
    val name: String,
    val playCount: String,
    val tags: Tags,
    val tracks: Tracks,
    val url: String,
    val wiki: Wiki
)