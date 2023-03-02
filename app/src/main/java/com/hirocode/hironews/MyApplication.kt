package com.hirocode.hironews

import android.app.Application
import com.hirocode.hironews.di.networkModule
import com.hirocode.hironews.di.repositoryModule
import com.hirocode.hironews.di.useCaseModule
import com.hirocode.hironews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}