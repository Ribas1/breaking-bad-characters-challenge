package com.pedroribeiro.breakingbadcharacterschallenge.repositories

import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter
import io.reactivex.Single

interface CharactersRepository {
    fun getCharacters(): Single<List<BreakingBadCharacter>>
}
