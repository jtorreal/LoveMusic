package com.josele.lovemusic.data.remote.repository

import com.josele.lovemusic.data.local.datasource.ILoveMusicLocalDataSource
import com.josele.lovemusic.data.mapper.toDataTopAlbumList
import com.josele.lovemusic.data.remote.datasource.ILoveMusicRemoteDataSource
import com.josele.lovemusic.data.remote.network.ErrorMessages
import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.domain.repository.ILoveMusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoveMusicRepositoryImpl @Inject constructor(
    private val iLoveMusicRemoteDataSource: ILoveMusicRemoteDataSource,
    private val iLoveMusicLocalDataSource: ILoveMusicLocalDataSource
) : ILoveMusicRepository {
    override fun getTopAlbumList(artistName: String): Flow<Result<List<TopAlbumDomain>>> = flow {

        when (val response = iLoveMusicRemoteDataSource.getTopAlbumListSource(artistName)) {
            is Result.Success -> {
                iLoveMusicLocalDataSource.saveTopAlbumList(response.data)
                emit(response)
            }

            is Result.Error -> {
                when (val localResponse =
                    iLoveMusicLocalDataSource.getTopAlbumListSource(artistName)) {
                    is Result.Error -> emit(Result.Error(message = ErrorMessages.GENERAL_ERROR))
                    is Result.Success -> emit(localResponse)
                }
            }
        }
    }

    override  fun getTopAlbumInfo(
        artistName: String,
        albumName: String
    ): Flow<Result<TopAlbumInfoDomain>> = flow {
        when (val response =
            iLoveMusicRemoteDataSource.getTopAlbumInfoSource(artistName, albumName)) {
            is Result.Error -> iLoveMusicLocalDataSource.getTopAlbumInfoSource(
                artistName,
                albumName
            )

            is Result.Success -> emit(response)
        }
    }

}