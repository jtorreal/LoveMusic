package com.josele.lovemusic

import android.content.Context
import com.josele.lovemusic.domain.model.Tags
import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.domain.model.Tracks
import com.josele.lovemusic.domain.model.Wiki
import com.josele.lovemusic.domain.usecase.GetTopAlbumInfoUseCase
import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.presentation.viewmodel.TopAlbumInfoViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TopAlbumInfoViewModelTest {

    private lateinit var mContextMock: Context

    @RelaxedMockK
    lateinit var getTopAlbumInfoUseCase: GetTopAlbumInfoUseCase

    private lateinit var viewModel: TopAlbumInfoViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = TopAlbumInfoViewModel(getTopAlbumInfoUseCase)
        mContextMock = mockk<Context>(relaxed = true)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time to return album info, the result is error`() =
        runTest {
            val fakeUsername = "AlejandroSanz"
            val fakeAlbumName = "Más"

            val errorMessage = "Error fetching album info"
            every { getTopAlbumInfoUseCase.invoke(fakeUsername, fakeAlbumName) } returns flow {
                emit(Result.Error(message = errorMessage))
            }

            viewModel.loadTopAlbumInfo()

            verify { getTopAlbumInfoUseCase.invoke(fakeUsername, fakeAlbumName) }

            assertEquals(viewModel.topAlbumInfoUiState.value.isLoading, false)

            assertEquals(
                viewModel.topAlbumInfoUiState.value.errorMessage?.asString(mContextMock),
                errorMessage
            )

        }

    @Test
    fun `when viewmodel is created at the first time to return album info, the result is success`() =
        runTest {
            val fakeUsername = "AlejandroSanz"
            val fakeAlbumName = "Más"

            every { getTopAlbumInfoUseCase.invoke(fakeUsername, fakeAlbumName) } returns flow {
                emit(Result.Success(topAlbumInfoDomain))
            }

            viewModel.loadTopAlbumInfo()

            verify { getTopAlbumInfoUseCase.invoke(fakeUsername, fakeAlbumName) }

            assertEquals(viewModel.topAlbumInfoUiState.value.isLoading, false)

            assertEquals(
                viewModel.topAlbumInfoUiState.value.item,
                topAlbumInfoDomain
            )

        }

    private val topAlbumInfoDomain = TopAlbumInfoDomain(
        artist = "Sanz",
        image = emptyList(),
        listeners = "",
        mbid = "",
        name = "Alejandro",
        playCount = "2000",
        tags = Tags(emptyList()),
        tracks = Tracks(emptyList()),
        url = "url", wiki = Wiki("", "", "")
    )
}