package com.pedroribeiro.breakingbadcharacterschallenge.di

import com.pedroribeiro.breakingbadcharacterschallenge.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel() }
}