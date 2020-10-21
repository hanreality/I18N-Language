package com.beibei.android.language

import android.app.Application
import com.beibei.android.language.lifecycle.I18NActivityLifecycleCallbacks
import com.beibei.android.language.lifecycle.I18NComponentLifecycleCallbacks

/**
 * Created by han.chen.
 * Date on 2020/10/20.
 **/
object I18NPlugin {

    private var i18NActivityLifecycleCallbacks: I18NActivityLifecycleCallbacks? = null
    private var i18NComponentLifecycleCallbacks: I18NComponentLifecycleCallbacks?  = null

    // 注册 LifecycleCallbacks
    private fun registerLifecycleCallbacks(application: Application) {
        i18NActivityLifecycleCallbacks = I18NActivityLifecycleCallbacks()
        i18NComponentLifecycleCallbacks = I18NComponentLifecycleCallbacks(application)
        application.registerActivityLifecycleCallbacks(i18NActivityLifecycleCallbacks)
        application.registerComponentCallbacks(i18NComponentLifecycleCallbacks)
    }

    // 注销 LifecycleCallbacks
    private fun unregisterLifecycleCallbacks(application: Application) {
        i18NActivityLifecycleCallbacks?.let {
            application.unregisterActivityLifecycleCallbacks(it)
            i18NActivityLifecycleCallbacks = null
        }
        i18NComponentLifecycleCallbacks?.let {
            application.unregisterComponentCallbacks(it)
            i18NComponentLifecycleCallbacks = null
        }
    }

    fun init(application: Application, updateInterfaceWay: Int = I18NHelper.RECREATE_CURRENT_ACTIVITY): I18NPlugin {
        return this.apply {

            // 初始化所有用到的工具类
            I18NActivityHelper.init(updateInterfaceWay)
            I18NSPHelper.init(application)
            I18NHelper.init(application)

            registerLifecycleCallbacks(application)

            // 缓存系统语言
            I18NHelper.getInstance().cacheSystemLocale()
            // 设置语言
            I18NHelper.getInstance().updateApplicationContext(application)
        }
    }

    // App 结束时调用
    fun terminate(application: Application) {
        unregisterLifecycleCallbacks(application)
        BroadcastReceiverManager.unregisterAllBroadcastReceiver()
    }
}