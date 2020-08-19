package com.pedroribeiro.breakingbadcharacterschallenge.di

import com.pedroribeiro.breakingbadcharacterschallenge.network.schedulers.SchedulerProvider
import com.pedroribeiro.breakingbadcharacterschallenge.network.schedulers.SchedulerProviderImpl
import org.koin.dsl.module

val schedulersModule = module {
    single<SchedulerProvider> { SchedulerProviderImpl() }
}