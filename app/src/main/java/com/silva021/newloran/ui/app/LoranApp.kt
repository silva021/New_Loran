package com.silva021.newloran.ui.app

import android.app.Application
import com.silva021.newloran.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LoranApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LoranApp)
            modules(appModule)
        }
    }
}