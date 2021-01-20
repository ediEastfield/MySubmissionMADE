package com.dicoding.picodiploma.mysubmissionmade

import android.app.Application
import com.dicoding.picodiploma.mysubmissionmade.core.di.databaseModule
import com.dicoding.picodiploma.mysubmissionmade.core.di.networkModule
import com.dicoding.picodiploma.mysubmissionmade.core.di.repositoryModule
import com.dicoding.picodiploma.mysubmissionmade.di.useCaseModule
import com.dicoding.picodiploma.mysubmissionmade.di.viewModelModule
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
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}