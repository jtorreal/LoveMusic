package com.josele.lovemusic.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josele.lovemusic.domain.usecase.GetTopAlbumsUseCase
import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.presentation.ui.state.TopAlbumListUiState
import com.josele.lovemusic.presentation.ui.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAlbumListViewModel @Inject constructor(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase
) : ViewModel() {

    private val defaultArtist = "AlejandroSanz"
    private val _stateAlbumList = MutableStateFlow(TopAlbumListUiState())
    val stateAlbumList: StateFlow<TopAlbumListUiState> = _stateAlbumList.asStateFlow()

    init {
        loadTopAlbums()
    }

    fun loadTopAlbums() {

        viewModelScope.launch(Dispatchers.IO) {
            _stateAlbumList.value = TopAlbumListUiState(isLoading = true)
            getTopAlbumsUseCase.invoke(defaultArtist).collect { result ->
                when (result) {
                    is Result.Error ->
                        _stateAlbumList.value = TopAlbumListUiState(
                            isLoading = false,
                            errorMessage = UiText.DynamicString(result.message)
                        )

                    is Result.Success -> {
                        _stateAlbumList.value =
                            TopAlbumListUiState(isLoading = false, items = result.data)
                    }
                }
            }
        }
    }
}