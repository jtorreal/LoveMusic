package com.josele.lovemusic.domain.repository

import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import kotlinx.coroutines.flow.Flow

interface ILoveMusicRepository {
      fun getTopAlbumList(artistName: String):Flow<Result<List<TopAlbumDomain>>>
      fun getTopAlbumInfo(artistName: String, albumName: String): Flow<Result<TopAlbumInfoDomain>>
}