package com.wreckingballsoftware.magicdex.ui

import android.app.Application
import com.wreckingballsoftware.magicdex.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MagicDexApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(androidContext = this@MagicDexApp)
            modules(modules = appModule)
        }
    }
}