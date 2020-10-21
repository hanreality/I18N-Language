package com.hanreality.language

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import com.beibei.android.language.I18NHelper

/**
 * Created by han.chen.
 * Date on 2020/10/20.
 **/
open class BaseActivity :AppCompatActivity() {

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        overrideConfiguration?.setLocale(I18NHelper.getInstance().getSetLocale())
        super.applyOverrideConfiguration(overrideConfiguration)
    }
}