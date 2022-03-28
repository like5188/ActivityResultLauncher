package com.like.activityresultlauncher

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat

class RequestMultiplePermissionsLauncher(caller: ActivityResultCaller) :
    BaseActivityResultLauncher<Array<String>, Map<String, Boolean>>(
        caller, ActivityResultContracts.RequestMultiplePermissions()
    ) {

    /**
     * @return
     * key：权限；
     * value：first：是否允许权限；second；权限被拒绝时，是否不再提示。
     */
    suspend fun requestMultiplePermissions(
        vararg permissions: String,
        options: ActivityOptionsCompat? = null
    ): Map<String, Pair<Boolean, Boolean>> {
        return super.launch(permissions.toList().toTypedArray(), options).mapValues {
            if (it.value) {
                true to false
            } else {
                false to !ActivityCompat.shouldShowRequestPermissionRationale(activity, it.key)
            }
        }
    }

    fun requestMultiplePermissions(
        vararg permissions: String,
        options: ActivityOptionsCompat? = null,
        callback: ActivityResultCallback<Map<String, Pair<Boolean, Boolean>>>
    ) {
        super.launch(permissions.toList().toTypedArray(), options) {
            callback.onActivityResult(
                it.mapValues {
                    if (it.value) {
                        true to false
                    } else {
                        false to !ActivityCompat.shouldShowRequestPermissionRationale(activity, it.key)
                    }
                }
            )
        }
    }
}