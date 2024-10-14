package com.josele.lovemusic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.josele.lovemusic.presentation.ui.screen.TopAlbumInfoScreen
import com.josele.lovemusic.presentation.ui.screen.TopAlbumListScreen
import com.josele.lovemusic.presentation.viewmodel.TopAlbumInfoViewModel
import com.josele.lovemusic.presentation.viewmodel.TopAlbumListViewModel

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TopAlbumList) {
        composable<TopAlbumList> {
            val topAlbumListViewModel = hiltViewModel<TopAlbumListViewModel>()
            TopAlbumListScreen(viewModel = topAlbumListViewModel,
                onClick = { albumId ->
                    navController.navigate(TopAlbumInfo(albumId))
                })
        }

        composable<TopAlbumInfo> { backStackEntry ->
            val albumInfoDetail = backStackEntry.toRoute<TopAlbumInfo>()
            val topAlbumInfoViewModel = hiltViewModel<TopAlbumInfoViewModel>()
            TopAlbumInfoScreen(topAlbumInfoViewModel)
        }
    }
}
