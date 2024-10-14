package com.josele.lovemusic

import com.josele.lovemusic.data.remote.api.response.model.topalbums.Artist
import com.josele.lovemusic.data.remote.repository.LoveMusicRepositoryImpl
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.domain.usecase.GetTopAlbumsUseCase
import com.josele.lovemusic.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class TopAlbumListUseCaseTest {

    @RelaxedMockK
    private lateinit var topAlbumRepository: LoveMusicRepositoryImpl

    lateinit var getTopAlbumsUseCase: GetTopAlbumsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTopAlbumsUseCase = GetTopAlbumsUseCase(topAlbumRepository)
    }

    @Test
    fun `when the api return on success result and data in albums list`() = runTest {

        val artistName = "AlejandroSanz"

        //Given
        every { topAlbumRepository.getTopAlbumList(artistName) } returns flowAlbumList

        //When
        getTopAlbumsUseCase(artistName)

        //Then
        coVerify(exactly = 1) { topAlbumRepository.getTopAlbumList(artistName) }
    }

    @Test
    fun `when the api return on success result and data in albums detail`() = runTest {

        val artistName = "AlejandroSanz"
        val albumName = "Más"

        //Given
        every { topAlbumRepository.getTopAlbumInfo(artistName,albumName) } returns flowAlbumInfo

        //When
        getTopAlbumsUseCase(artistName)

        //Then
        coVerify(exactly = 1) { topAlbumRepository.getTopAlbumList(artistName) }
    }

    private val flowAlbumList: Flow<Result<List<TopAlbumDomain>>> = flow {
        Result.Success(listTopDomain)
    }

    private val flowAlbumInfo: Flow<Result<TopAlbumInfoDomain>> = flow {
        Result.Success(listTopDomain)
    }

    private val listTopDomain = listOf(
        TopAlbumDomain(Artist("12345", "AlejandroSanz", "http"), emptyList(), "", "", 3, "")
    )
}