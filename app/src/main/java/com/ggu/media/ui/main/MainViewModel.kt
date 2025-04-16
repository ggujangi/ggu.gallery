package com.ggu.media.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ggu.media.ui.permission.PermissionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _permissionState: MutableStateFlow<PermissionState> =
        MutableStateFlow(PermissionState.Denied)
    val permissionState: StateFlow<PermissionState> = _permissionState

    fun updatePermissionState(checkPermission: () -> (PermissionState)) {
        viewModelScope.launch {
            _permissionState.value = checkPermission.invoke()
        }
    }
}