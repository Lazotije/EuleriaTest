package com.example.euleriatask

import android.app.Application
import com.example.euleriatask.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EuleriaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EuleriaApplication)
            modules(appModule)
        }
    }
}