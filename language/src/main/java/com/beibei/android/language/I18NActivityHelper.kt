package com.beibei.android.language

import android.content.Context
import android.content.Intent

/**
 * Created by han.chen.
 * Date on 2020/10/20.
 **/
class I18NActivityHelper {

    interface OnUpdateInterfaceListener {
        fun updateInterface(context: Context, intent: Intent?)
    }

    private var onUpdateInterfaceListener: OnUpdateInterfaceListener? = null

    fun setOnUpdateInterfaceListener(onUpdateInterfaceListener: OnUpdateInterfaceListener) {
        this.onUpdateInterfaceListener = onUpdateInterfaceListener
    }

    // 默认方式是 recreate()
    private var interfaceUpdateWay = -1

    // 若调用时参数为空，则默认方式， recreate()
    fun setInterfaceUpdateWay(updateInterfaceWay: Int = I18NHelper.RECREATE_CURRENT_ACTIVITY) {
        this.interfaceUpdateWay = updateInterfaceWay
    }

    fun getInterfaceUpdateWay(): Int {
        if (interfaceUpdateWay == -1) {
            throw IllegalArgumentException("I18NActivityHelper.updateInterfaceWay should be initialized first")
        }
        return interfaceUpdateWay
    }

    /**
     * 跳转主页
     *
     * @param context
     * @param intent
     */
    fun openWithClearTask(context: Context, intent: Intent?) {
        intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
        // 杀掉进程，如果是跨进程则杀掉当前进程
//        android.os.Process.killProcess(android.os.Process.myPid())
//        System.exit(0);
    }

    /**
     * 重新加载Activity
     *
     * @param context
     */
    fun recreateActivity(context: Context) {
        // 使用广播也可以实现不重启到 LauncherActivity 只需 recreate() 即可刷新 Resources
        val intent = Intent(I18NHelper.ACTION_RECREATE_ACTIVITY)
        context.sendBroadcast(intent) // 发送广播
    }

    fun updateInterface(context: Context, intent: Intent?) {
        // if(xx != null) ... else ...
        onUpdateInterfaceListener?.updateInterface(context, intent)
            ?: throw IllegalArgumentException("The listener has not been initialized")
    }

    companion object {
        private lateinit var instance : I18NActivityHelper

        fun getInstance(): I18NActivityHelper {
            check(::instance.isInitialized) {
                "ActivityHelper should be initialized first, please check you are already LocalePlugin.init(...) in application"
            }
            return instance
        }

        fun initInterfaceUpdateWay(interfaceUpdateWay: Int): I18NActivityHelper{
            getInstance().setInterfaceUpdateWay(interfaceUpdateWay)
            return getInstance()
        }

        // internal 控制只能被 LocalePlugin 初始化
        internal fun init(interfaceUpdateWay: Int): I18NActivityHelper {
            check(!::instance.isInitialized) { "ActivityHelper is already initialized" }
            instance = I18NActivityHelper()
            getInstance().setInterfaceUpdateWay(interfaceUpdateWay)
            return instance
        }

        // internal 控制只能被 LocalePlugin 初始化
        internal fun init(): I18NActivityHelper {
            check(!::instance.isInitialized) { "ActivityHelper is already initialized" }
            instance = I18NActivityHelper()
            return instance
        }
    }
}