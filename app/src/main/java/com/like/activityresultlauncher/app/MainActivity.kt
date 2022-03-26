package com.like.activityresultlauncher.app

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.like.activityresultlauncher.RequestPermissionLauncher
import com.like.activityresultlauncher.StartActivityForResultLauncher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val requestPermissionLauncher = RequestPermissionLauncher(this)
    fun requestPermission(view: View) {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA) {
            Log.w("TAG", it.toString())
        }
    }

    fun requestMultiplePermissions(view: View) {
    }

    private val startActivityForResultLauncher1 = StartActivityForResultLauncher(this)
    private val startActivityForResultLauncher2 = StartActivityForResultLauncher(this)
    fun startActivityForResult(view: View) {
        TestActivity1.start(startActivityForResultLauncher1) {
            Log.w("TAG1", it.data?.getStringExtra("name") ?: "")
        }
//        TestActivity1.start(startActivityForResultLauncher2) {
//            Log.e("TAG2", it.data?.getStringExtra("name") ?: "")
//        }
    }

    fun startIntentSenderForResult(view: View) {
    }
}