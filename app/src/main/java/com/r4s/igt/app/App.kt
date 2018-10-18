package com.r4s.igt.app

import android.app.Application
import android.content.Context
import com.mobile.support.core.UtilsR
import com.prashantsolanki.secureprefmanager.SecurePrefManagerInit
import com.r4s.igt.BuildConfig
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

// Created by sebastian with ♥
class App : Application() {


    init {
        instance = this
    }

    companion object {
        private lateinit var instance: App
        fun appCtx(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        UtilsR.init(this)
        SecurePrefManagerInit.Initializer(applicationContext).useEncryption(true).initialize()

        if (BuildConfig.REPORTLOGS) {

            Timber.plant(Timber.DebugTree())

            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
        }
    }
}