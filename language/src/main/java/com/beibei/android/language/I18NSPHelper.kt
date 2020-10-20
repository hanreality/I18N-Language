package com.beibei.android.language

import android.content.Context
import org.json.JSONObject

/**
 * Created by han.chen.
 * Date on 2020/10/20.
 **/
class I18NSPHelper(private val context: Context) {

    fun getContext(): Context {
        return context
    }

    private fun getDefaultSharedPreferencesName(): String? {
        return context.packageName + "_preferences"
    }

    private fun getDefaultSharedPreferencesMode(): Int {
        return Context.MODE_PRIVATE
    }

    companion object {
        private lateinit var instance: I18NSPHelper

        private fun getInstance(): I18NSPHelper {
            check(Companion::instance.isInitialized) {
                "LocaleDefaultSPHelper should be initialized first, please check you are already LocalePlugin.init(...) in application"
            }
            return instance
        }

        var language: String
            get() = getInstance()
                .getContext().getSharedPreferences(
                getInstance().getDefaultSharedPreferencesName(),
                getInstance().getDefaultSharedPreferencesMode())
                .getString(
                    I18NHelper.LANGUAGE, JSONObject().put(I18NHelper.LANGUAGE, "auto").toString()
                ) ?: JSONObject().put(I18NHelper.LANGUAGE, "auto").toString()
            set(value) = getInstance()
                .getContext().getSharedPreferences(
                getInstance().getDefaultSharedPreferencesName(),
                getInstance().getDefaultSharedPreferencesMode()
            ).edit().putString(I18NHelper.LANGUAGE, value).apply()

        fun init(context: Context) : I18NSPHelper {
            check(!Companion::instance.isInitialized) {
                "LocaleDefaultSPHelper is already initialized"
            }
            instance =
                I18NSPHelper(context)
            return instance
        }
    }
}