package com.josele.lovemusic.data.remote.api

import com.josele.lovemusic.data.remote.api.response.model.TopAlbumsResponse
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.TopAlbumInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoveMusicApiClient {

    //EXAMPLE: https://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=cher&api_key=6c03e5ad289b0af1c1eb05c374ae13c0&format=json
    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbumsList(
        @Query("artist") artistName: String
    ): Response<TopAlbumsResponse>

    //EXAMPLE: https://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=YOUR_API_KEY&artist=Cher&album=Believe&format=json
    @GET("?method=artist.getinfo")
    suspend fun getTopAlbumInfo(
        @Query("artist") artistName: String,
        @Query("album") albumName: String
    ): Response<TopAlbumInfo>


}
