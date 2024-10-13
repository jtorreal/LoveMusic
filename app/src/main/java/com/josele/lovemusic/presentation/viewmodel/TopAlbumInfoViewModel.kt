package com.josele.lovemusic.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josele.lovemusic.domain.usecase.GetTopAlbumInfoUseCase
import com.josele.lovemusic.domain.utils.Result
import com.josele.lovemusic.presentation.ui.state.TopAlbumInfoUiState
import com.josele.lovemusic.presentation.ui.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopAlbumInfoViewModel @Inject constructor(
    private val getTopAlbumInfoUseCase: GetTopAlbumInfoUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _topAlbumInfoUiState = MutableStateFlow(TopAlbumInfoUiState(isLoading = true))
    val topAlbumInfoUiState: StateFlow<TopAlbumInfoUiState> = _topAlbumInfoUiState.asStateFlow()

    init {
        loadTopAlbumInfo()
    }

    private fun loadTopAlbumInfo() {

        viewModelScope.launch(Dispatchers.IO) {
            _topAlbumInfoUiState.value = TopAlbumInfoUiState(isLoading = true)
            getTopAlbumInfoUseCase.invoke("AlejandroSanz","Más").collect { result ->
                when (result) {
                    is Result.Error ->
                        _topAlbumInfoUiState.value = TopAlbumInfoUiState(
                            isLoading = false,
                            errorMessage = UiText.DynamicString(result.message)
                        )

                    is Result.Success -> {
                        _topAlbumInfoUiState.value =
                            TopAlbumInfoUiState(isLoading = false, item = result.data)
                    }
                }
            }
        }
    }
}