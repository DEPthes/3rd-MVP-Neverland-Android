package com.neverland.thinkerbell.presentation.base

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "settings")

class ThinkerBellApplication: Application() {

    companion object {
        lateinit var application: ThinkerBellApplication
    }

    override fun onCreate() {
        super.onCreate()

        application = this
    }
}