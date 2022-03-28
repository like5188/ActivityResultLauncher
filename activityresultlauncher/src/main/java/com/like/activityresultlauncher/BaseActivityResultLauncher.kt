package com.like.activityresultlauncher

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.MainThread
import androidx.core.app.ActivityOptionsCompat
import com.like.activityresultlauncher.util.activity
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

/*
 * 用于注册结果、启动结果以及在系统分派结果后对其进行处理
 * 注意：注册必须在Activity#onStart()方法之前，所以创建 Launcher 时不能使用 by lazy{}，只能直接 new 对象，否则会报错。
 * 例子：private val xxxLauncher = XxxLauncher(caller)
 */

open class BaseActivityResultLauncher<I, O>(caller: ActivityResultCaller, contract: ActivityResultContract<I, O>) {
    val activity = caller.activity
    private var continuation: CancellableContinuation<O>? = null
    private var callback: ActivityResultCallback<O>? = null
    private val launcher = caller.registerForActivityResult(contract) {
        callback?.onActivityResult(it)
        callback = null
        continuation?.resume(it)
        continuation?.cancel()
        continuation = null
    }

    suspend fun launch(input: I, options: ActivityOptionsCompat? = null): O = withContext(Dispatchers.Main) {
        suspendCancellableCoroutine {
            continuation = it
            launcher.launch(input, options)
        }
    }

    @MainThread
    fun launch(input: I, options: ActivityOptionsCompat? = null, callback: ActivityResultCallback<O>) {
        this.callback = callback
        launcher.launch(input, options)
    }

    @MainThread
    fun unregister() {
        launcher.unregister()
    }
}