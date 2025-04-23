package com.ggu.media.ui.media

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ggu.media.R

@Composable
fun PartialPermissionHeader(
    onRequestPermission: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.message_media_permission_partial_granted),
            style = MaterialTheme.typography.titleSmall
        )
        Button(onClick = onRequestPermission) {
            Text(
                text = stringResource(id = R.string.button_media_permission_manage)
            )
        }
    }
}

@Preview
@Composable
fun PartialPermissionHeaderPreview() {
    PartialPermissionHeader {

    }
}