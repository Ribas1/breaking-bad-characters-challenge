package com.pedroribeiro.breakingbadcharacterschallenge.network

import com.pedroribeiro.breakingbadcharacterschallenge.network.models.GetCharactersResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BreakingBadApi {

    @GET("characters")
    fun getCharacters(): Single<GetCharactersResponse>
}