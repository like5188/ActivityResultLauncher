package com.like.activityresultlauncher

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.MainThread
import androidx.core.app.ActivityOptionsCompat
import com.like.activityresultlauncher.util.createIntent

/**
 * 注意：当目标界面的 launchMode 为 singleInstance、 singleTask 时，会出现以下问题：
 *
 * 一、如果只启动一个目标界面：
 * 1、api >= 30               能收到返回值。
 * 2、api >= 21 && api <= 29  能收到返回值。
 * 3、api == 19               收不到返回值。
 *
 * 二、如果启动多个目标界面：
 * 1、在 api >= 30               launchMode 正常（只能启动一个），  并且能收到一个返回值。
 * 2、api >= 21 && api <= 29     launchMode 无效（会启动多个），   在最后一个目标界面关闭时，才能收到所有返回值。
 * 3、api == 19                  launchMode 正常（只能启动一个），  但是收不到返回值。
 *
 * 综上所述：api >= 30 时，一切正常。
 */
class StartActivityForResultLauncher(caller: ActivityResultCaller) :
    BaseActivityResultLauncher<Intent, ActivityResult>(
        caller, ActivityResultContracts.StartActivityForResult()
    ) {

    suspend inline fun <reified T : Activity> launch(
        vararg params: Pair<String, Any?>,
        options: ActivityOptionsCompat? = null
    ): ActivityResult = launch(activity.createIntent<T>(*params), options)

    @MainThread
    inline fun <reified T : Activity> launch(
        vararg params: Pair<String, Any?>,
        options: ActivityOptionsCompat? = null,
        callback: ActivityResultCallback<ActivityResult>
    ) {
        launch(activity.createIntent<T>(*params), options, callback)
    }

}