package com.josele.lovemusic.data.local.datasource

import com.josele.lovemusic.data.remote.api.response.model.topalbums.Album
import com.josele.lovemusic.data.remote.api.response.model.topalbums.TopAlbums
import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain

interface ILoveMusicLocalDataSource {
    suspend fun getTopAlbumListSource(artistName: String): Result<List<TopAlbumDomain>>
    suspend fun getTopAlbumInfoSource(artistName: String, albumName: String): Result<TopAlbumInfoDomain>
    suspend fun saveTopAlbumList(topAlbumList: List<TopAlbumDomain>)
}