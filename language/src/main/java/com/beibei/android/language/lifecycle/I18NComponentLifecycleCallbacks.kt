package com.beibei.android.language.lifecycle

import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import com.beibei.android.language.I18NHelper

class I18NComponentLifecycleCallbacks(private val context: Context)  : ComponentCallbacks2 {

    override fun onLowMemory() { }

    override fun onTrimMemory(level: Int) { }

    override fun onConfigurationChanged(newConfig: Configuration) {
        I18NHelper.getInstance().onConfigurationChanged(context)
    }
}