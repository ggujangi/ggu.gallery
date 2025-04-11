package com.ggu.media.data.model

data class PhotoData(
    override val id: Int,
    override val imgRes: Int,
    override val extension: String
) : MediaData