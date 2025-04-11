package com.ggu.media.data.model

data class VideoData(
    override val id: Int,
    override val imgRes: Int,
    override val extension: String,
    val duration: Long
) : MediaData