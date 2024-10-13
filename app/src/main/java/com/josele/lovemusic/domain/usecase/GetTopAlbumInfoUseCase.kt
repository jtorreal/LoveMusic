package com.josele.lovemusic.domain.usecase

import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.domain.repository.ILoveMusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAlbumInfoUseCase @Inject constructor(private val iLoveMusic: ILoveMusicRepository) {

    operator fun invoke(artistName: String, albumName: String): Flow<Result<TopAlbumInfoDomain>> {
        return iLoveMusic.getTopAlbumInfo(artistName, albumName)
    }
}