package com.pedroribeiro.breakingbadcharacterschallenge.network

import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadApi {

    @GET("characters")
    fun getCharacters(): Single<List<BreakingBadCharacter>>

    @GET("characters")
    fun searchCharacter(
        @Query("name") query: String
    ): Single<List<BreakingBadCharacter>>

}