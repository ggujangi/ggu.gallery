package com.ggu.media.ui.media

import androidx.lifecycle.ViewModel
import com.ggu.media.R
import com.ggu.media.domain.MediaUiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MediaListViewModel : ViewModel() {
    private val _mediaItems = MutableStateFlow<List<MediaUiData>>(emptyList())
    val mediaItems: StateFlow<List<MediaUiData>> = _mediaItems

    init {
        _mediaItems.value = mutableListOf<MediaUiData>().apply {
            repeat(10) {
                add(MediaUiData.MediaPhotoUiData(R.drawable.test, "jpg"))
                add(MediaUiData.MediaPhotoUiData(R.drawable.test, "jpg"))
                add(MediaUiData.MediaPhotoUiData(R.drawable.test, "gif"))
                add(MediaUiData.MediaPhotoUiData(R.drawable.test, "jpg"))
                add(MediaUiData.MediaPhotoUiData(R.drawable.test, "jpg"))
                add(MediaUiData.MediaPhotoUiData(R.drawable.test, "jpg"))
                add(MediaUiData.MediaVideoUiData(R.drawable.test, "mp4", 1440))
            }
        }
    }

}