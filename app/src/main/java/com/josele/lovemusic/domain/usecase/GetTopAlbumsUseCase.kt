package com.josele.lovemusic.domain.usecase

import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.repository.ILoveMusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAlbumsUseCase @Inject constructor(private val iLoveMusic: ILoveMusicRepository) {

    operator fun invoke(artistName: String): Flow<Result<List<TopAlbumDomain>>> {
        return iLoveMusic.getTopAlbumList(artistName)
    }
}