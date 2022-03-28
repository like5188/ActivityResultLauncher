package com.like.activityresultlauncher

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts

class RequestPermissionLauncher(caller: ActivityResultCaller) :
    BaseActivityResultLauncher<String, Boolean>(
        caller, ActivityResultContracts.RequestPermission()
    )