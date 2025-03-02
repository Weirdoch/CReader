package com.example.creader.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import com.example.creader.constant.PreferKey
import com.example.creader.utils.getPreInt
import com.example.creader.utils.getPreString
import com.example.creader.utils.sysConfiguration
import java.util.Locale

object AppContextWrapper {

    @SuppressLint("ObsoleteSdkInt")
    fun wrap(context: Context) : Context {
        // 获取当前上下文资源对象和配置对象
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        // 获取当前设置语言
        val targetLocale: Locale = getSetLocale(context)
        // 设置当前语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(targetLocale)
            configuration.setLocales(LocaleList(targetLocale))
        } else {
            @Suppress("DEPRECATION")
            configuration.locale = targetLocale
        }
        // 设置当前字体缩放比例
        configuration.fontScale = getFontScale(context)
        // 返回当前上下文资源对象和配置对象
        return context.createConfigurationContext(configuration)
    }

    /**
     * 当前系统字体缩放比例
     */
    private fun getFontScale(context: Context): Float {
        var fontScale = context.getPreInt(PreferKey.fontScale) / 10f
        // 如果系统字体缩放比例不在0.8-1.6之间，则使用系统字体缩放比例
        if (fontScale !in 0.8f..1.6f) {
            fontScale = sysConfiguration.fontScale
        }
        return fontScale
    }

    /**
     * 当前系统语言
     */
    @SuppressLint("ObsoleteSdkInt")
    fun getSystemLocale(): Locale {
        val locale: Locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 如果系统语言列表不为空，则使用系统语言列表的第一个语言
            locale = sysConfiguration.locales.get(0)
        } else {
            // 如果系统语言列表为空，则使用系统语言
            @Suppress("DEPRECATION")
            locale = sysConfiguration.locale
        }
        return locale
    }

    /**
     * 获取当前设置语言
     */
    private fun getSetLocale(context: Context): Locale {
        // 根据当前设置语言返回对应的Locale对象
        return when (context.getPreString(PreferKey.language)) {
            "zh" -> Locale.SIMPLIFIED_CHINESE
            "tw" -> Locale.TRADITIONAL_CHINESE
            "en" -> Locale.ENGLISH
            else -> getSystemLocale()
        }
    }

    //TODO 判断App语言和设置语言是否相同

}