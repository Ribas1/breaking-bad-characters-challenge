package com.pedroribeiro.breakingbadcharacterschallenge.di

import com.pedroribeiro.breakingbadcharacterschallenge.mappers.CharacterMapper
import org.koin.dsl.module

val mapperModule = module {
    single { CharacterMapper() }
}