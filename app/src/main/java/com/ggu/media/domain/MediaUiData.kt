package com.ggu.media.domain

sealed class MediaUiData() {
    data class MediaPhotoUiData(
        val imageResId: Int,
        val extension: String
    ) : MediaUiData()

    data class MediaVideoUiData(
        val imageResId: Int,
        val extension: String,
        val duration: Long
    ) : MediaUiData()
}