package com.like.activityresultlauncher.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.like.activityresultlauncher.requestMultiplePermissions
import com.like.activityresultlauncher.requestPermission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun requestPermission1(view: View) {
        requestPermission(Manifest.permission.CAMERA) {
            Log.w("TAG", it.toString())
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA, it)
        }
    }

    fun requestPermission2(view: View) {
        lifecycleScope.launch(Dispatchers.IO) {
            val granted = requestPermission(Manifest.permission.CAMERA)
            Log.w("TAG", granted.toString())
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA, granted)
        }
    }

    private fun shouldShowRequestPermissionRationale(permission: String, granted: Boolean): Boolean {
        /*
         * 我们可以通过该方法判断是否要继续申请权限
         * 1、api >= 30，没有"不再提示"选择框让用户勾选，系统会在拒绝两次后直接不再提示。
         * 2、用户可以在权限被拒绝后使用此方法来判断是否属于"不再提示"。
         * ①返回 true 表示用户没有勾选"不再提示"。
         * ②返回 false 表示用户勾选了"不再提示"。
         */
        val result = !granted && !ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
        if (result) {
            // 用户选择 "不再询问" 后的提示方案
            AlertDialog.Builder(this@MainActivity)
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
                .create()
                .show()
        }
        return result
    }

    fun requestMultiplePermissions(view: View) {
        requestMultiplePermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) {
            var s = false
            it.forEach {
                Log.w("TAG", "${it.key} ${it.value}")
                if (!s) {
                    s = shouldShowRequestPermissionRationale(it.key, it.value)
                }
            }
        }
    }

    fun startActivityForResult1(view: View) {
        TestActivity1.start(this) {
            Log.w("TAG1", "resultCode=${it.resultCode} data=${it.data?.getStringExtra("name")}")
        }
    }

    fun startActivityForResult2(view: View) {
        lifecycleScope.launch(Dispatchers.IO) {
            val result = TestActivity1.start(this@MainActivity)
            Log.w("TAG1", "resultCode=${result.resultCode} data=${result.data?.getStringExtra("name")}")
        }
    }

    fun startIntentSenderForResult(view: View) {
    }

}
