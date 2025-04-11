package com.ggu.media.ui.media

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ggu.media.data.model.PhotoData
import com.ggu.media.data.model.VideoData
import com.ggu.media.domain.MediaUiData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MediaListViewModel : ViewModel() {

    val mediaItems: Flow<PagingData<MediaUiData>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            MediaListPagingSource()
        }
    ).flow.map { pagingData ->
        pagingData.map { mediaData ->
            when (mediaData) {
                is PhotoData -> MediaUiData.MediaPhotoUiData(
                    imageResId = mediaData.imgRes,
                    extension = mediaData.extension
                )

                is VideoData -> MediaUiData.MediaVideoUiData(
                    imageResId = mediaData.imgRes,
                    extension = mediaData.extension,
                    duration = mediaData.duration
                )
            }
        }
    }

}