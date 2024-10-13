package com.josele.lovemusic.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.josele.lovemusic.data.local.database.entity.TopAlbumInfoRoom
import com.josele.lovemusic.data.local.database.entity.TopAlbumListRoom

@Database(entities = [TopAlbumListRoom::class, TopAlbumInfoRoom::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TopAlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): TopAlbumDao
}