package com.like.activityresultlauncher.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.like.activityresultlauncher.StartActivityForResultLauncher
import com.like.activityresultlauncher.app.databinding.ActivityTest1Binding

/**
 * 正常的 Activity
 */
class TestActivity1 : AppCompatActivity() {
    companion object {
        fun start(
            startActivityForResultLauncher: StartActivityForResultLauncher,
            callback: ActivityResultCallback<ActivityResult>
        ) {
            val intent = Intent(startActivityForResultLauncher.activity, TestActivity1::class.java)
            startActivityForResultLauncher.launch(intent, callback = callback)
        }
    }

    private val mBinding by lazy {
        DataBindingUtil.setContentView<ActivityTest1Binding>(this, R.layout.activity_test1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding
    }

    fun click0(view: View) {
        setResult(Activity.RESULT_OK, Intent().apply { putExtra("name", "123") })
        finish()
    }
}
