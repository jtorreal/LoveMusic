package com.josele.lovemusic.di

import android.content.Context
import androidx.room.Room
import com.josele.lovemusic.data.local.database.TopAlbumDao
import com.josele.lovemusic.data.local.database.TopAlbumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTopAlbumDatabase(@ApplicationContext appContext: Context): TopAlbumDatabase {
        return Room.databaseBuilder(
            appContext,
            TopAlbumDatabase::class.java,
            "top_album.db"
        ).build()
    }

    @Provides
    fun provideTopAlbumDao(appDatabase: TopAlbumDatabase): TopAlbumDao {
        return appDatabase.albumDao()
    }
}




