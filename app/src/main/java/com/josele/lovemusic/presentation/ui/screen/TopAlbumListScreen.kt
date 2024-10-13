package com.josele.lovemusic.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.josele.lovemusic.R
import com.josele.lovemusic.domain.model.TopAlbumDomain
import com.josele.lovemusic.presentation.ui.state.TopAlbumListUiState
import com.josele.lovemusic.presentation.viewmodel.TopAlbumListViewModel

@Composable
fun TopAlbumListScreen(viewModel: TopAlbumListViewModel, onClick: (String)  -> Unit) {

    val topAlbumListViewState: TopAlbumListUiState by viewModel.stateAlbumList.collectAsStateWithLifecycle()

    if (topAlbumListViewState.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(topAlbumListViewState.items) { item ->
                Album(album = item, onClick)
            }
        }
    }
}

@Composable
fun Album(album: TopAlbumDomain, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClick(album.name)
            },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {

        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = album.image[0].text,
                contentDescription = stringResource(R.string.top_albums_screen_image_item)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = album.name, fontWeight = FontWeight.Bold)
                Text(text = "Playcount: ${album.playCount}")
            }
        }
    }
}

