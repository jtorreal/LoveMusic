package com.josele.lovemusic.data.local.datasource

import android.database.sqlite.SQLiteException
import com.josele.lovemusic.data.local.database.TopAlbumDao
import com.josele.lovemusic.data.mapper.toDataTopAlbumList
import com.josele.lovemusic.data.mapper.toDomainFromAlbumInfoDao
import com.josele.lovemusic.data.mapper.toDomainFromAlbumListDao
import com.josele.lovemusic.data.mapper.toEntityFromApi
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Album
import com.josele.lovemusic.data.remote.api.response.model.topalbums.TopAlbums
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.domain.utils.Result
import javax.inject.Inject

class LoveMusicLocalDataSourceImpl @Inject constructor(private val topAlbumDao: TopAlbumDao) :
    ILoveMusicLocalDataSource {

    override suspend fun getTopAlbumListSource(artistName: String): Result<List<TopAlbumDomain>> {

        return try {

            val localResponse = topAlbumDao.getTopAlbumList().toDomainFromAlbumListDao()
            Result.Success(localResponse)

        } catch (e: SQLiteException) {
            Result.Error(exception = e, message = e.localizedMessage ?: "")
        }
    }

    override suspend fun getTopAlbumInfoSource(
        artistName: String,
        albumName: String
    ): Result<TopAlbumInfoDomain> {
        return try {

            //TODO: TO pass id object to recover
            val localResponse = topAlbumDao.getTopAlbumInfo("id".toInt()).toDomainFromAlbumInfoDao()
            Result.Success(localResponse)

        } catch (e: SQLiteException) {
            Result.Error(exception = e, message = e.localizedMessage ?: "")
        }
    }

    override suspend fun saveTopAlbumList(topAlbumList: List<TopAlbumDomain>) {
        topAlbumDao.insertTopAlbumList(topAlbumList.toDataTopAlbumList().toEntityFromApi())
    }
}