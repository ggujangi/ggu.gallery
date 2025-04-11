package com.ggu.media.data.model

sealed interface MediaData {
    val id: Int
    val imgRes: Int // 임시 값
    val extension: String
}