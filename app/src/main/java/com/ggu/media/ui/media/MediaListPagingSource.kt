package com.ggu.media.ui.media

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ggu.media.R
import com.ggu.media.data.model.MediaData
import com.ggu.media.data.model.PhotoData
import com.ggu.media.data.model.VideoData

class MediaListPagingSource : PagingSource<Int, MediaData>() {

    private val totalItems = 100_000
    private val mediaItems = (1..totalItems).map {
        if (it % 2 == 0) {
            PhotoData(id = it, imgRes = R.drawable.test, extension = "jpg")
        } else {
            VideoData(id = it, imgRes = R.drawable.test, extension = "mp4", duration = it.toLong())
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaData> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        val start = page * pageSize
        val end = minOf(start + pageSize, totalItems)

        return kotlin.runCatching {
            val data = mediaItems.subList(start, end)

            val nextKey = if (end < totalItems) page + 1 else null

            val prevKey = if (start > 0) page - 1 else null
            LoadResult.Page(
                data = data,
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