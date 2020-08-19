package com.pedroribeiro.breakingbadcharacterschallenge.home

import com.pedroribeiro.breakingbadcharacterschallenge.models.BreakingBadSeason
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterUiModel

class SeasonFilterHelper {
    fun filterNot(
        season: BreakingBadSeason,
        list: List<CharacterUiModel>?
    ): List<CharacterUiModel>? {
        return list?.filter { character ->
            character.appearances.any {
                it == season || it == BreakingBadSeason.ALL
            }
        }
    }
}
