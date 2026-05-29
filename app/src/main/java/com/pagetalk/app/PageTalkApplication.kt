package com.pagetalk.app

import android.app.Application
import com.pagetalk.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class PageTalkApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            AndroidLogger()
            androidContext(this@PageTalkApplication)
            modules(appModule)
        }
    }

}