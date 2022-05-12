package com.like.activityresultlauncher.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.like.activityresultlauncher.app.databinding.ActivityTest1Binding
import com.like.activityresultlauncher.startActivityForResult

/**
 * 正常的 Activity
 */
class TestActivity1 : AppCompatActivity() {
    companion object {
        fun start(
            activity: ComponentActivity,
            callback: ActivityResultCallback<ActivityResult>
        ) {
            activity.startActivityForResult<TestActivity1>(callback = callback)
        }

        suspend fun start(activity: ComponentActivity): ActivityResult {
            return activity.startActivityForResult<TestActivity1>()
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
