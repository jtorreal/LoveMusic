package com.josele.lovemusic.presentation.ui.state

import com.josele.lovemusic.domain.model.TopAlbumInfoDomain
import com.josele.lovemusic.presentation.ui.utils.UiText

data class TopAlbumInfoUiState(
    val isLoading: Boolean = false,
    val item: TopAlbumInfoDomain? = null,
    val errorMessage: UiText? = null
)

