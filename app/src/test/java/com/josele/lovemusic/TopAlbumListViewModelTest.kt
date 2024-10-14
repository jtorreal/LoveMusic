package com.josele.lovemusic

import app.cash.turbine.test
import com.josele.lovemusic.data.local.datasource.ILoveMusicLocalDataSource
import com.josele.lovemusic.data.remote.api.response.model.Album
import com.josele.lovemusic.data.remote.api.response.model.Artist
import com.josele.lovemusic.data.remote.datasource.ILoveMusicRemoteDataSource
import com.josele.lovemusic.data.remote.repository.LoveMusicRepositoryImpl
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.domain.usecase.GetTopAlbumsUseCase
import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.presentation.ui.state.TopAlbumListUiState
import com.josele.lovemusic.presentation.ui.utils.UiText
import com.josele.lovemusic.presentation.viewmodel.TopAlbumListViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TopAlbumListViewModelTest {

    @MockK
    private lateinit var getTopAlbumsUseCase: GetTopAlbumsUseCase

    private lateinit var loveMusicRepositoryImpl: LoveMusicRepositoryImpl

    private lateinit var loveMusicRemoteDataSource : ILoveMusicRemoteDataSource

    private lateinit var loveMusicLocalDataSource : ILoveMusicLocalDataSource

    private lateinit var viewModel: TopAlbumListViewModel

    @Before
    fun setUp() {
        loveMusicRemoteDataSource = mockk()
        loveMusicLocalDataSource = mockk()
        loveMusicRepositoryImpl = LoveMusicRepositoryImpl(loveMusicRemoteDataSource,loveMusicLocalDataSource)
        getTopAlbumsUseCase =
            GetTopAlbumsUseCase(loveMusicRepositoryImpl) // Crear un mock del caso de uso
        viewModel = TopAlbumListViewModel(getTopAlbumsUseCase)
    }

    @Test
    fun `loadTopAlbums success`() = runTest {

        val _stateFlow = MutableStateFlow(TopAlbumListUiState())
        val intSharedFlow = _stateFlow.asStateFlow()

        _stateFlow.value = TopAlbumListUiState(isLoading = true)

        coEvery { getTopAlbumsUseCase.invoke(any()) } returns flowListSuccess

        // Define el comportamiento del mock
        coEvery { loveMusicRepositoryImpl.getTopAlbumList(any()) } returns flowListSuccess


        // Recolectar los álbumes emitidos
        val emittedAlbums = mutableListOf<List<String>>()
        getTopAlbumsUseCase.invoke("AlejandroSanz").collect { albums ->
            _stateFlow.value = TopAlbumListUiState(isLoading = false)
            assertEquals(intSharedFlow.value.isLoading, false)
            assertEquals(topDomainList, topDomainList)
        }
    }

    @Test
    fun `loadTopAlbums error`() = runTest {
        val _stateFlow = MutableStateFlow(TopAlbumListUiState())
        val intSharedFlow = _stateFlow.asStateFlow()

        val errorMessage = "Error fetching albums"
        _stateFlow.value = TopAlbumListUiState(isLoading = true)
        coEvery { getTopAlbumsUseCase.invoke(any()) } returns flowListError

        viewModel.stateAlbumList.test {
            _stateFlow.value = TopAlbumListUiState(
                isLoading = false,
                errorMessage = UiText.DynamicString(errorMessage)
            )
            assertEquals(intSharedFlow.value.isLoading, false)
        }
    }

    private val flowListSuccess = flow<Result<List<TopAlbumDomain>>> {
        Result.Success(topDomainList)
    }

    private val flowListError = flow<Result<List<TopAlbumDomain>>> {
        Result.Error(message = "Error fetching albums")
    }

    private val topDomainList = Album(Artist("", "", ""), image = emptyList(), "", "", 2, "http")
}