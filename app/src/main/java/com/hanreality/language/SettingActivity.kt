package com.hanreality.language

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*
import java.util.*

/**
 * Created by han.chen.
 * Date on 2020/10/20.
 **/
class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        EN.setOnClickListener {
            com.beibei.android.language.I18NUtils.setLanguage(this@SettingActivity, Locale.ENGLISH)
        }
    }
}