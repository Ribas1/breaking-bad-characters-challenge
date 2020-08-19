package com.pedroribeiro.breakingbadcharacterschallenge.repositories

import com.pedroribeiro.breakingbadcharacterschallenge.network.BreakingBadApi
import com.pedroribeiro.breakingbadcharacterschallenge.network.exceptions.SearchEmptyException
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter
import io.reactivex.Single

class CharactersRepositoryImpl(
    private val breakingBadApi: BreakingBadApi
) : CharactersRepository {
    override fun getCharacters(): Single<List<BreakingBadCharacter>> {
        return breakingBadApi.getCharacters()
    }

    override fun searchCharacter(query: String): Single<List<BreakingBadCharacter>> {
        return breakingBadApi.searchCharacter(query)
            .flatMap { characters ->
                if (characters.isNullOrEmpty()) {
                    Single.error(SearchEmptyException())
                } else {
                    Single.just(characters)
                }
            }
    }

}
