package com.pedroribeiro.breakingbadcharacterschallenge

import android.app.Application
import com.pedroribeiro.breakingbadcharacterschallenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                viewModelModule,
                networkModule,
                repositoryModule,
                mapperModule,
                schedulersModule
            )
        }
    }
}