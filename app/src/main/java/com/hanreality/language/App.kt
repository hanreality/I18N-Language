package com.hanreality.language

import android.app.Application
import com.beibei.android.language.I18NHelper
import com.beibei.android.language.I18NPlugin

/**
 * Created by han.chen.
 * Date on 2020/10/20.
 **/
class App :Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        I18NPlugin.init(instance, I18NHelper.RECREATE_CURRENT_ACTIVITY)
    }
}