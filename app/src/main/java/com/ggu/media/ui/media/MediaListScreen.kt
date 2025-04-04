package com.ggu.media.ui.media

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ggu.media.R
import com.ggu.media.domain.MediaUiData

@Composable
fun MediaListScreen(items: List<MediaUiData>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items) {
            when (it) {
                is MediaUiData.MediaPhotoUiData -> MediaPhotoItem(it)
                is MediaUiData.MediaVideoUiData -> MediaVideoItem(it)
            }
        }
    }
}

@Preview
@Composable
fun MediaListScreenPreview() {
    MediaListScreen(
        listOf(
            MediaUiData.MediaPhotoUiData(R.drawable.test, "jpg"),
            MediaUiData.MediaPhotoUiData(R.drawable.test, "gif"),
            MediaUiData.MediaVideoUiData(R.drawable.test, "mp4", 1440),
            MediaUiData.MediaVideoUiData(R.drawable.test, "mp4", 2440),
        )
    )
}