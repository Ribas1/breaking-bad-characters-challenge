package com.pedroribeiro.breakingbadcharacterschallenge.di

import com.pedroribeiro.breakingbadcharacterschallenge.repositories.CharactersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CharactersRepository> {
        com.pedroribeiro.breakingbadcharacterschallenge.repositories.CharactersRepositoryImpl(
            get()
        )
    }
}