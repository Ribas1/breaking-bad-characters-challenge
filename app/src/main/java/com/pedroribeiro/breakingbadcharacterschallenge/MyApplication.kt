package com.pedroribeiro.breakingbadcharacterschallenge

import android.app.Application
import com.pedroribeiro.breakingbadcharacterschallenge.di.mapperModule
import com.pedroribeiro.breakingbadcharacterschallenge.di.networkModule
import com.pedroribeiro.breakingbadcharacterschallenge.di.repositoryModule
import com.pedroribeiro.breakingbadcharacterschallenge.di.viewModelModule
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
                mapperModule
            )
        }
    }
}