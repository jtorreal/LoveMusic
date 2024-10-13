package com.josele.lovemusic.data.remote.datasource

import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.domain.model.TopAlbumDomain

interface ILoveMusicRemoteDataSource {
     suspend fun getTopAlbumListSource(artistName: String): Result<List<TopAlbumDomain>>
     suspend fun getTopAlbumInfoSource(artistName: String, albumName: String): Result<TopAlbumInfoDomain>
}