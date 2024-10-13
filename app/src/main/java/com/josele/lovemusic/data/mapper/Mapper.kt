package com.josele.lovemusic.data.mapper

import com.josele.lovemusic.data.local.database.entity.TopAlbumInfoRoom
import com.josele.lovemusic.data.local.database.entity.TopAlbumListRoom
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Album
import com.josele.lovemusic.domain.model.Artist
import com.josele.lovemusic.domain.model.Attr
import com.josele.lovemusic.domain.model.Image
import com.josele.lovemusic.domain.model.Streamable
import com.josele.lovemusic.domain.model.Tag
import com.josele.lovemusic.domain.model.Tags
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.domain.model.Track
import com.josele.lovemusic.domain.model.Tracks
import com.josele.lovemusic.domain.model.Wiki
import com.josele.lovemusic.data.remote.api.response.model.topalbuminfo.Album as AlbumInfo


fun List<Album>.toDomainTopAlbumList(): List<TopAlbumDomain> {
    return map { album ->
        TopAlbumDomain(
            artist = album.artist,
            image = album.image.filter {
                it.size == "extralarge"
            },
            mBid = album.mBid.toString(),
            name = album.name.toString(),
            playCount = album.playcount.toDefaultInteger(),
            url = album.url.toEmptyString()
        )
    }
}

fun List<TopAlbumDomain>.toDataTopAlbumList(): List<Album> {
    return map { album ->
        Album(
            artist = album.artist,
            image = album.image,
            mBid = album.mBid,
            name = album.name,
            playcount = album.playCount,
            url = album.url
        )
    }
}

fun AlbumInfo.toDomainTopAlbumInfo(): TopAlbumInfoDomain =
    TopAlbumInfoDomain(
        artist = artist,
        image = image.map {
            Image(size = it.size, text = it.text)
        },
        name = name,
        listeners = listeners,
        playCount = playcount,
        mbid = mbid,
        tags = Tags(tags.tag.map { Tag(it.name, it.url) }),
        tracks = Tracks(tracks.track.map {
            Track(
                name = name,
                artist = Artist(mbid, name, url),
                url = url,
                attr = Attr(it.attr.rank),
                streamable = Streamable(it.streamable.fulltrack, it.streamable.text),
                duration = it.duration
            )
        }),
        url = url,
        wiki = Wiki(wiki.content, wiki.summary, wiki.published)
    )

fun List<Album>.toEntityFromApi(): List<TopAlbumListRoom> {
    return map { album ->
        TopAlbumListRoom(
            id = album.mBid.toEmptyString().toInt(),
            artist = album.artist,
            image = album.image,
            mBid = album.mBid.toEmptyString(),
            name = album.name.toEmptyString(),
            playCount = album.playcount.toDefaultInteger(),
            url = album.url.toEmptyString()
        )
    }
}

//ROOM
fun List<TopAlbumListRoom>.toDomainFromAlbumListDao(): List<TopAlbumDomain> {
    return map { album ->
        TopAlbumDomain(
            artist = album.artist,
            image = album.image,
            mBid = album.mBid,
            name = album.name,
            playCount = album.playCount,
            url = album.url
        )
    }
}

fun TopAlbumInfoRoom.toDomainFromAlbumInfoDao(): TopAlbumInfoDomain =
    TopAlbumInfoDomain(
        artist = this.artist,
        image = image.map {
            Image(size = it.size, text = it.text)
        },
        listeners = this.listeners,
        mbid = this.mBid,
        name = this.name,
        playCount = this.playCount,
        tags = Tags(tags.tag.map { Tag(it.name, it.url) }),
        tracks = Tracks(tracks.track.map {
            Track(
                name = name,
                artist = Artist(mBid, name, url),
                url = url,
                attr = Attr(it.attr.rank),
                streamable = Streamable(it.streamable.fulltrack, it.streamable.text),
                duration = it.duration
            )
        }),
        url = this.url,
        wiki = Wiki(wiki.content, wiki.summary, wiki.published)
    )

fun String?.toEmptyString(): String {
    if (this == null) {
        return ""
    }
    return toString()
}

fun Int?.toDefaultInteger(): Int {
    if (this == null) return 0
    return this
}


