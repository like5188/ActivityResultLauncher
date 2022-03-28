package com.like.activityresultlauncher

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.MainThread
import androidx.core.app.ActivityOptionsCompat

class RequestMultiplePermissionsLauncher(caller: ActivityResultCaller) :
    BaseActivityResultLauncher<Array<String>, Map<String, Boolean>>(
        caller, ActivityResultContracts.RequestMultiplePermissions()
    ) {
    suspend fun launch(vararg permissions: String, options: ActivityOptionsCompat? = null): Map<String, Boolean> =
        super.launch(arrayOf(*permissions), options)

    @MainThread
    fun launch(vararg permissions: String, options: ActivityOptionsCompat? = null, callback: ActivityResultCallback<Map<String, Boolean>>) {
        super.launch(arrayOf(*permissions), options, callback)
    }
}