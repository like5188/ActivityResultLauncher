package com.like.activityresultlauncher

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts

/*
private val requestPermissionLauncher = RequestPermissionLauncher(this)
requestPermissionLauncher.launch(android.Manifest.permission.CAMERA) {
    //shouldShowRequestPermissionRationale()
    //注意：从 Android 30 开始，没有不再提示选择，系统会在拒绝两次后直接不再提示。
    //如果返回true表示用户点了禁止获取权限，但没有勾选不再提示。
    //返回false表示用户点了禁止获取权限，并勾选不再提示。
    //我们可以通过该方法判断是否要继续申请权限
    if (!it && !ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {
        // 用户选择 "不再询问" 后的提示方案
        AlertDialog.Builder(this)
            .setTitle("授权失败")
            .setMessage("您需要授权此权限才能使用此功能")
            .setPositiveButton("去授权") { dialog, which -> // 跳转到设置界面
                val intent = Intent()
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                intent.data = Uri.fromParts("package", packageName, null)
                startActivity(intent)
            }
            .setNegativeButton("取消") { dialog, which -> }
            .create().show()
    }
}
 */

/**
 * 注意：
 * 1、权限必须在 [AndroidManifest.xml] 文件中声明，然后再动态请求才有效。
 * 2、api >= 30，没有"不再提示"选择框让用户勾选，系统会在拒绝两次后直接不再提示。
 * 3、用户可以在权限被拒绝后使用 [ActivityCompat.shouldShowRequestPermissionRationale] 方法来判断是否属于不再提示。
 * ①返回 true 表示用户没有勾选不再提示。
 * ②返回 false 表示用户勾选了不再提示。
 */
class RequestPermissionLauncher(caller: ActivityResultCaller) :
    BaseActivityResultLauncher<String, Boolean>(
        caller, ActivityResultContracts.RequestPermission()
    )