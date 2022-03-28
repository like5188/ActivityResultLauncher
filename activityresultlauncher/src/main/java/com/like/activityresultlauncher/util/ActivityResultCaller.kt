package com.like.activityresultlauncher.util

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCaller
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

val ActivityResultCaller.activity: Activity
    get() {
        return when (this) {
            is ComponentActivity -> this
            is Fragment -> activity ?: throw IllegalStateException("Fragment $this not attached to Activity")
            else -> throw IllegalStateException("$this must be androidx.activity.ComponentActivity or androidx.fragment.app.Fragment")
        }
    }

val ActivityResultCaller.lifecycleOwner: LifecycleOwner
    get() {
        return when (this) {
            is LifecycleOwner -> this
            else -> throw IllegalStateException("$this must be androidx.lifecycle.LifecycleOwner")
        }
    }

val ActivityResultCaller.context: Context
    get() {
        return when (this) {
            is ComponentActivity -> this
            is Fragment -> requireContext()
            else -> throw IllegalStateException("$this must be androidx.activity.ComponentActivity or androidx.fragment.app.Fragment")
        }
    }

/**
 * 1、api >= 30，没有"不再提示"选择框让用户勾选，系统会在拒绝两次后直接不再提示。
 * 2、用户可以在权限被拒绝后使用此方法来判断是否属于"不再提示"。
 * ①返回 true 表示用户没有勾选"不再提示"。
 * ②返回 false 表示用户勾选了"不再提示"。
 */
/*
private val requestPermissionLauncher = RequestPermissionLauncher(this)
requestPermissionLauncher.launch(android.Manifest.permission.CAMERA) {
    //shouldShowRequestPermissionRationale()
    //注意：从 Android 30 开始，没有不再提示选择，系统会在拒绝两次后直接不再提示。
    //如果返回true表示用户点了禁止获取权限，但没有勾选不再提示。
    //返回false表示用户点了禁止获取权限，并勾选不再提示。
    //我们可以通过该方法判断是否要继续申请权限
    if (!it && !this.shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
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
fun ActivityResultCaller.shouldShowRequestPermissionRationale(permission: String): Boolean {
    return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
}
