package com.pedroribeiro.breakingbadcharacterschallenge.di

import com.pedroribeiro.breakingbadcharacterschallenge.BuildConfig.BASE_URL
import com.pedroribeiro.breakingbadcharacterschallenge.network.BreakingBadApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideBreakingBadApi(get()) }
}

fun provideBreakingBadApi(retrofit: Retrofit): BreakingBadApi =
    retrofit.create(BreakingBadApi::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create()).build()
}


fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
    .build()