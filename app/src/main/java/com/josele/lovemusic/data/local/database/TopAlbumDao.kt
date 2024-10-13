package com.josele.lovemusic.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.josele.lovemusic.data.local.database.entity.TopAlbumInfoRoom
import com.josele.lovemusic.data.local.database.entity.TopAlbumListRoom

@Dao
interface TopAlbumDao {

    @Query("SELECT * FROM albumList")
    fun getTopAlbumList(): List<TopAlbumListRoom>

    @Query("SELECT * FROM albumInfo WHERE id = :id")
    fun getTopAlbumInfo(id: Int): TopAlbumInfoRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopAlbumList(roomListSuperHero: List<TopAlbumListRoom>)
}

