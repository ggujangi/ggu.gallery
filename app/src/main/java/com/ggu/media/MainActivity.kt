package com.ggu.media

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ggu.media.ui.main.MainScreen
import com.ggu.media.ui.main.MainViewModel
import com.ggu.media.ui.permission.PermissionScreen
import com.ggu.media.ui.permission.PermissionState
import com.ggu.media.ui.theme.MediaProjectTheme
import com.ggu.media.utils.isGranted

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaProjectTheme {
                val permissionState by viewModel.permissionState.collectAsState()
                val permissionLauncher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { results ->
                        checkMediaPermissionState(this)
                    }
                when (permissionState) {
                    is PermissionState.Denied -> PermissionScreen {
                        requestMediaPermission(permissionLauncher)
                    }

                    else -> MainScreen(
                        permissionState = permissionState,
                        onRequestPermission = {
                            requestMediaPermission(permissionLauncher)
                        })
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updatePermissionState {
            checkMediaPermissionState(this)
        }
    }

    private fun requestMediaPermission(
        launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            launcher.launch(
                arrayOf(
                    READ_MEDIA_IMAGES,
                    READ_MEDIA_VIDEO,
                    READ_MEDIA_VISUAL_USER_SELECTED
                )
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            launcher.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
        } else {
            launcher.launch(arrayOf(READ_EXTERNAL_STORAGE))
        }
    }

    private fun checkMediaPermissionState(context: Context): PermissionState {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            (READ_MEDIA_IMAGES.isGranted(context) || READ_MEDIA_VIDEO.isGranted(context))
        ) {
            // Full access on Android 13 (API level 33) or higher
            PermissionState.Granted
        } else if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE &&
            READ_MEDIA_VISUAL_USER_SELECTED.isGranted(context)
        ) {
            // Partial access on Android 14 (API level 34) or higher
            PermissionState.PartialGranted
        } else if (READ_EXTERNAL_STORAGE.isGranted(context)) {
            // Full access up to Android 12 (API level 32)
            PermissionState.Granted
        } else {
            // Access denied
            PermissionState.Denied
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MediaProjectTheme {
        Greeting("Android")
    }
}