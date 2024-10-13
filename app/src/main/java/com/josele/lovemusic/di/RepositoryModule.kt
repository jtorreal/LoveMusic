package com.josele.lovemusic.di

import com.josele.lovemusic.data.local.database.TopAlbumDao
import com.josele.lovemusic.data.local.datasource.ILoveMusicLocalDataSource
import com.josele.lovemusic.data.local.datasource.LoveMusicLocalDataSourceImpl
import com.josele.lovemusic.data.remote.api.LoveMusicApiClient
import com.josele.lovemusic.data.remote.datasource.ILoveMusicRemoteDataSource
import com.josele.lovemusic.data.remote.datasource.LoveMusicRemoteDataSourceImpl
import com.josele.lovemusic.data.remote.repository.LoveMusicRepositoryImpl
import com.josele.lovemusic.domain.repository.ILoveMusicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideILoveMusicRemoteDataSource(
        loveMusicApiClient: LoveMusicApiClient
    ): ILoveMusicRemoteDataSource {
        return LoveMusicRemoteDataSourceImpl(loveMusicApiClient)
    }

    @Provides
    fun provideILoveMusicLocalDataSource(
        topAlbumDao: TopAlbumDao
    ): ILoveMusicLocalDataSource {
        return LoveMusicLocalDataSourceImpl(topAlbumDao)
    }

    @Provides
    fun provideILoveMusicRepository(
        remoteDataSource: ILoveMusicRemoteDataSource,
        localDataSource: ILoveMusicLocalDataSource
    ): ILoveMusicRepository {
        return LoveMusicRepositoryImpl(remoteDataSource, localDataSource)
    }

}