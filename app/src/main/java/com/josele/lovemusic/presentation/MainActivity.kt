package com.josele.lovemusic.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.josele.lovemusic.R
import com.josele.lovemusic.presentation.navigation.MyApp
import com.josele.lovemusic.presentation.ui.component.CustomAppBar
import com.josele.lovemusic.presentation.ui.theme.LoveMusicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoveMusicTheme {
                Scaffold(
                    topBar = {
                        CustomAppBar(
                            title = stringResource(R.string.top_albums_screen_top_bar),
                            iconRes = R.drawable.ic_last_fm, // Reemplaza con tu recurso de ícono
                            onIconClick = { /* Manejar el clic del ícono aquí */ }
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                ) { paddingValue ->
                    Box(
                        modifier = Modifier
                            .padding(paddingValue)
                            .fillMaxSize()
                    ) {
                        MyApp()
                    }
                }
            }
        }
    }
}
