package com.pedroribeiro.breakingbadcharacterschallenge.repositories

import com.pedroribeiro.breakingbadcharacterschallenge.network.BreakingBadApi
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.GetCharactersResponse
import io.reactivex.Single

class CharactersRepositoryImpl(
    private val breakingBadApi: BreakingBadApi
) : CharactersRepository {
    override fun getCharacters(): Single<GetCharactersResponse> {
        return breakingBadApi.getCharacters()
    }

}
