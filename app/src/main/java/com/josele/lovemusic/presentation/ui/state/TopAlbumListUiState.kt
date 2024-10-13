package com.josele.lovemusic.presentation.ui.state

import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.presentation.ui.utils.UiText

data class TopAlbumListUiState(
    val isLoading: Boolean = false,
    val items: List<TopAlbumDomain> = emptyList(),
    val errorMessage: UiText? = null
)