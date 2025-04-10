package com.ggu.media.ui.media

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ggu.media.data.model.MediaData

class MediaListPagingSource : PagingSource<Int, MediaData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaData> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        return kotlin.runCatching {
            val mediaItems = emptyList<MediaData>()

            val nextKey = if (mediaItems.isNotEmpty()) {
                page + 1
            } else {
                null
            }

            val prevKey = if (page > 0) page - 1 else null
            LoadResult.Page(
                data = mediaItems,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }.getOrElse { LoadResult.Error(it) }
    }

    override fun getRefreshKey(state: PagingState<Int, MediaData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}