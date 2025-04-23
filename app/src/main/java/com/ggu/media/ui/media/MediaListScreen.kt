package com.ggu.media.ui.media

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.ggu.media.domain.MediaUiData
import com.ggu.media.ui.permission.PermissionState

@Composable
fun MediaListScreen(
    permissionState: PermissionState,
    onRequestPermission: () -> Unit,
    items: LazyPagingItems<MediaUiData>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (permissionState == PermissionState.PartialGranted) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                PartialPermissionHeader(onRequestPermission)
            }
        }
        items(items.itemCount) { index ->
            val item = items[index]
            item?.let {
                when (it) {
                    is MediaUiData.MediaPhotoUiData -> MediaPhotoItem(it)
                    is MediaUiData.MediaVideoUiData -> MediaVideoItem(it)
                }
            }
        }
    }
}