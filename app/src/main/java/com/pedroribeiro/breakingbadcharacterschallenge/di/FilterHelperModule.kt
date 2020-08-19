package com.pedroribeiro.breakingbadcharacterschallenge.di

import com.pedroribeiro.breakingbadcharacterschallenge.home.SeasonFilterHelper
import org.koin.dsl.module

val filterHelper = module {
    single { SeasonFilterHelper() }
}