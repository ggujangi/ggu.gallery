package com.ggu.media.ui.permission

sealed interface PermissionState {
    data object Granted : PermissionState
    data object PartialGranted : PermissionState
    data object Denied : PermissionState
}