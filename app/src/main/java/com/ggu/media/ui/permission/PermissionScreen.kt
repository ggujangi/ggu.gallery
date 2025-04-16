package com.ggu.media.ui.permission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PermissionScreen(
    onClickButton: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Permission is required.",

            )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onClickButton.invoke()
            },
        ) {
            Text(
                text = "Go to allow permission"
            )
        }
    }
}

@Preview
@Composable
fun PermissionScreenPreview() {
    PermissionScreen {


    }
}