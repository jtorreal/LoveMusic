package com.josele.lovemusic.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.josele.lovemusic.data.local.database.entity.TopAlbumListRoom
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Tags
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Tracks
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Wiki
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Artist
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Image

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromArtist(artist: Artist?): String? {
        return artist?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toArtist(artistString: String?): Artist? {
        return artistString?.let { gson.fromJson(it, Artist::class.java) }
    }

    @TypeConverter
    fun fromImageList(images: List<Image>?): String? {
        return images?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toImageList(imagesString: String?): List<Image>? {
        val listType = object : TypeToken<List<Image>>() {}.type
        return imagesString?.let { gson.fromJson(it, listType) } ?: listOf()
    }

    @TypeConverter
    fun fromTags(tags: Tags?): String? {
        return tags?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toTags(tagsString: String?): Tags? {
        return tagsString?.let { gson.fromJson(it, Tags::class.java) }
    }

    @TypeConverter
    fun fromTracks(tracks: Tracks?): String? {
        return tracks?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toTracks(tracksString: String?): Tracks? {
        return tracksString?.let { gson.fromJson(it, Tracks::class.java) }
    }

    @TypeConverter
    fun fromWiki(wiki: Wiki?): String? {
        return wiki?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toWiki(wikiString: String?): Wiki? {
        return wikiString?.let { gson.fromJson(it, Wiki::class.java) }
    }
}