package com.ggu.media.ui.media

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MediaScreen(
    mediaListViewModel: MediaListViewModel = viewModel()
) {
    val items by mediaListViewModel.mediaItems.collectAsState()
    MediaListScreen(items = items)
}