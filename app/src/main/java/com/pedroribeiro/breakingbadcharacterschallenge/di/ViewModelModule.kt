package com.pedroribeiro.breakingbadcharacterschallenge.di

import com.pedroribeiro.breakingbadcharacterschallenge.details.CharacterDetailsViewModel
import com.pedroribeiro.breakingbadcharacterschallenge.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel(get(), get(), get()) }
    factory { CharacterDetailsViewModel() }
}