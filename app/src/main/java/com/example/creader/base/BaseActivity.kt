package com.example.creader.base


import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.creader.constant.Theme

abstract class BaseActivity<VB : ViewBinding>(
    val fullScreen: Boolean = true,
    private val theme: Theme = Theme.Auto,
    private val toolBarTheme: Theme = Theme.Auto,
    private val transparent: Boolean = false,
    private val imageBg: Boolean = true
) : AppCompatActivity() {

    protected abstract val binding: VB

    // 是否为多窗口模式
    val isInMultiWindow: Boolean
        @SuppressLint("ObsoleteSdkInt")
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            isInMultiWindowMode
        } else {
            false
        }

    override fun attachBaseContext(newBase: Context) {
        // 设置语言
        super.attachBaseContext(AppContextWrapper.wrap(newBase))
    }



}