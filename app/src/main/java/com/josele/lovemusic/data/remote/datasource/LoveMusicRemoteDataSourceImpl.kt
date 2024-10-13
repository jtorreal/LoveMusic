package com.josele.lovemusic.data.remote.datasource

import com.josele.lovemusic.data.mapper.toDomainTopAlbumList
import com.josele.lovemusic.data.mapper.toDomainTopAlbumInfo
import com.josele.lovemusic.data.remote.api.LoveMusicApiClient
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Artist
import com.josele.lovemusic.data.remote.network.ErrorMessages
import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.data.remote.network.getMessageException
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.domain.model.TopAlbumDomain
import retrofit2.HttpException
import javax.inject.Inject

class LoveMusicRemoteDataSourceImpl @Inject constructor(
    private val loveMusicApiClient: LoveMusicApiClient,
) : ILoveMusicRemoteDataSource {
    override suspend fun getTopAlbumListSource(artistName: String): Result<List<TopAlbumDomain>> {

        return try {

            val response = loveMusicApiClient.getTopAlbumsList(artistName)

            return if (response.isSuccessful) {

                response.body()?.topalbums?.albumList?.let { albumList ->
                    if (albumList.isNotEmpty()) {
                        Result.Success(albumList.toDomainTopAlbumList())
                    } else {
                        Result.Error(
                            message = ErrorMessages.GENERAL_ERROR
                        )
                    }
                } ?: Result.Error(message = ErrorMessages.GENERAL_ERROR)

            } else {
                Result.Error(message = ErrorMessages.GENERAL_ERROR)
            }

        } catch (e: HttpException) {
            Result.Error(exception = e, message = getMessageException(e))
        }

    }


    override suspend fun getTopAlbumInfoSource(
        artistName: String,
        albumName: String
    ): Result<TopAlbumInfoDomain> {

        return try {

            val response = loveMusicApiClient.getTopAlbumInfo(artistName, albumName)

            if (response.isSuccessful) {
                response.body()?.album?.let { album ->
                    Result.Success(album.toDomainTopAlbumInfo())
                } ?: Result.Error(message = response.body()?.message ?: ErrorMessages.GENERAL_ERROR)

            } else {
                Result.Error(message = response.body()?.message ?: ErrorMessages.GENERAL_ERROR)
            }

        } catch (e: HttpException) {
            Result.Error(exception = e, message = getMessageException(e))
        }
    }
}




