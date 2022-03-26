package com.like.activityresultlauncher

import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts

class StartIntentSenderForResultLauncher(caller: ActivityResultCaller) :
    BaseActivityResultLauncher<IntentSenderRequest, ActivityResult>(
        caller, ActivityResultContracts.StartIntentSenderForResult()
    )