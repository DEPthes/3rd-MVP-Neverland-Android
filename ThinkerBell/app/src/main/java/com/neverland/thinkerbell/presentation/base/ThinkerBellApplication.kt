package com.neverland.thinkerbell.presentation.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.provider.Settings
import androidx.datastore.preferences.preferencesDataStore
import com.neverland.thinkerbell.core.utils.LoggerUtil

val Context.dataStore by preferencesDataStore(name = "settings")

class ThinkerBellApplication: Application() {

    companion object {
        lateinit var application: ThinkerBellApplication
    }

    override fun onCreate() {
        super.onCreate()

        application = this
    }

    @SuppressLint("HardwareIds")
    fun getAndroidId(): String {
        val androidId = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        LoggerUtil.i("Android Id: $androidId")
        return androidId ?: ""
    }
}