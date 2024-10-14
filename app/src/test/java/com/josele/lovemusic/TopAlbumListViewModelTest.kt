package com.josele.lovemusic

import android.content.Context
import com.josele.lovemusic.data.remote.api.response.model.topalbums.Artist
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.usecase.GetTopAlbumsUseCase
import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.presentation.viewmodel.TopAlbumListViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TopAlbumListViewModelTest {

    private lateinit var mContextMock: Context

    @RelaxedMockK
    lateinit var getTopAlbumsUseCase: GetTopAlbumsUseCase

    private lateinit var viewModel: TopAlbumListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = TopAlbumListViewModel(getTopAlbumsUseCase)
        mContextMock = mockk<Context>(relaxed = true)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time to return album list, the result is error`() = runTest {
        val fakeUsername = "AlejandroSanz"

        val errorMessage = "Error fetching albums"
        every { getTopAlbumsUseCase.invoke(fakeUsername) } returns flow {
            emit(Result.Error(message = errorMessage))
        }

        viewModel.loadTopAlbums()

        verify { getTopAlbumsUseCase.invoke(fakeUsername) }

        assertEquals(viewModel.stateAlbumList.value.isLoading, false)

        assertEquals(viewModel.stateAlbumList.value.errorMessage?.asString(mContextMock), errorMessage)

    }

    @Test
    fun `when viewmodel is created at the first time to return album list, the result is success`() = runTest {
        val fakeUsername = "AlejandroSanz"

        every { getTopAlbumsUseCase.invoke(fakeUsername) } returns flow {
            emit(Result.Success(listTopDomain))
        }

        viewModel.loadTopAlbums()

        verify { getTopAlbumsUseCase.invoke(fakeUsername) }

        assertEquals(viewModel.stateAlbumList.value.isLoading, false)

        assertEquals(viewModel.stateAlbumList.value.items, listTopDomain)

    }

    private val flowExample: Flow<Result<List<TopAlbumDomain>>> = flow {
        Result.Success(listTopDomain)
    }

    private val listTopDomain = listOf(
        TopAlbumDomain(Artist("12345", "AlejandroSanz", "http"), emptyList(), "", "", 3, "")
    )
}