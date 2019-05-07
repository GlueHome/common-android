package com.cesarferreira.sample

import android.app.Application
import com.gluehome.common.data.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}