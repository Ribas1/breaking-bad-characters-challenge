package com.pedroribeiro.breakingbadcharacterschallenge.repositories

import com.pedroribeiro.breakingbadcharacterschallenge.network.models.GetCharactersResponse
import io.reactivex.Single

interface CharactersRepository {
    fun getCharacters(): Single<GetCharactersResponse>
}
