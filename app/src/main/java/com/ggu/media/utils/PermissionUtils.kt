package com.ggu.media.utils

import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.content.ContextCompat

fun String.isGranted(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        this
    ) == PERMISSION_GRANTED
}