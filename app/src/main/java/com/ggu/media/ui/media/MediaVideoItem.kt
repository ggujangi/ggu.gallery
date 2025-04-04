package com.ggu.media.ui.media

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ggu.media.R
import com.ggu.media.domain.MediaUiData

@Composable
fun MediaVideoItem(videoData: MediaUiData.MediaVideoUiData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(id = videoData.imageResId),
            contentDescription = "동영상",
            contentScale = ContentScale.Crop
        )
        Surface(
            color = Color.Black.copy(alpha = 0.6f),
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = formatDuration(videoData.duration),
                    style = TextStyle(color = Color.White, fontSize = 12.sp),
                )
            }
        }
    }
}

private fun formatDuration(duration: Long): String {
    val hours = duration / 3600
    val minutes = (duration % 3600) / 60
    val seconds = duration % 60

    return if (hours > 0) {
        String.format("%d:%02d:%02d", hours, minutes, seconds)
    } else {
        String.format("%d:%02d", minutes, seconds)
    }
}

@Preview
@Composable
fun MediaVideoItemPreview() {
    MediaVideoItem(MediaUiData.MediaVideoUiData(R.drawable.test, "mp4", 1440))
}