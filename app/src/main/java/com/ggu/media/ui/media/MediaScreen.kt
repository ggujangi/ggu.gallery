package com.ggu.media.ui.media

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ggu.media.domain.MediaUiData
import com.ggu.media.ui.permission.PermissionState

@Composable
fun MediaScreen(
    permissionState: PermissionState,
    onRequestPermission: () -> Unit,
    mediaListViewModel: MediaListViewModel = viewModel()
) {
    val mediaItems: LazyPagingItems<MediaUiData> =
        mediaListViewModel.mediaItems.collectAsLazyPagingItems()
    MediaListScreen(
        permissionState = permissionState,
        onRequestPermission = onRequestPermission,
        items = mediaItems
    )
}