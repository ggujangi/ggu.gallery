package com.ggu.media.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ggu.media.ui.album.AlbumScreen
import com.ggu.media.ui.media.MediaScreen
import com.ggu.media.ui.permission.PermissionState
import com.ggu.media.ui.story.StoryScreen

@Composable
fun MainScreen(
    permissionState: PermissionState
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Media
        ) {
            composable<Media> { MediaScreen() }
            composable<Album> { AlbumScreen() }
            composable<Story> { StoryScreen() }
        }
    }
}