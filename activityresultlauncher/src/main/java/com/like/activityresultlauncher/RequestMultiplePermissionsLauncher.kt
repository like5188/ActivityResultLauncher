package com.like.activityresultlauncher

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts

class RequestMultiplePermissionsLauncher(caller: ActivityResultCaller) :
    BaseActivityResultLauncher<Array<String>, Map<String, Boolean>>(
        caller, ActivityResultContracts.RequestMultiplePermissions()
    )