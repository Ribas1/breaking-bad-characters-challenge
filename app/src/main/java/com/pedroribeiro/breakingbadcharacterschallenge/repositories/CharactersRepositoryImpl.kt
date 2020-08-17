package com.pedroribeiro.breakingbadcharacterschallenge.repositories

import com.pedroribeiro.breakingbadcharacterschallenge.network.BreakingBadApi
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter
import io.reactivex.Single

class CharactersRepositoryImpl(
    private val breakingBadApi: BreakingBadApi
) : CharactersRepository {
    override fun getCharacters(): Single<List<BreakingBadCharacter>> {
        return breakingBadApi.getCharacters()
    }

}
