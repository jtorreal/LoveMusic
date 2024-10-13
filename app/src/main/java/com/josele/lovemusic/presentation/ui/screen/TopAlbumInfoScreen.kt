package com.josele.lovemusic.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.josele.lovemusic.R
import com.josele.lovemusic.presentation.viewmodel.TopAlbumInfoViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
@Composable
fun TopAlbumInfoScreen(viewModel: TopAlbumInfoViewModel) {

    //val topAlbumInfoViewState: TopAlbumInfoViewModel by viewModel.topAlbumInfoUiState.collectAsStateWithLifecycle()


    // Define los márgenes
    val horizontalPadding = 16.dp
    val verticalPadding = 8.dp

    // Estructura de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = horizontalPadding) // Márgenes a los lados
            .padding(vertical = verticalPadding) // Márgenes verticales
            .verticalScroll(rememberScrollState()), // Permite el scroll si el contenido es largo
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen principal
        Image(
            painter = painterResource(id = R.drawable.ic_last_fm), // Reemplaza con tu recurso de imagen
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Ajusta la altura según lo necesites
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(verticalPadding))

        // Título
        Text(
            text = "Título de la Pantalla",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Subtítulo
        Text(
            text = "Subtítulo de la Pantalla",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Descripción
        Text(
            text = "Aquí va una descripción justificada que puede ser un poco más larga y contener información relevante sobre el contenido de la pantalla. Puedes agregar más texto aquí para hacer la descripción más detallada.",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = verticalPadding)
        )
    }
}