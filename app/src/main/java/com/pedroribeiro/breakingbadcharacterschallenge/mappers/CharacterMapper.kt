package com.pedroribeiro.breakingbadcharacterschallenge.mappers

import com.pedroribeiro.breakingbadcharacterschallenge.models.BreakingBadSeason
import com.pedroribeiro.breakingbadcharacterschallenge.models.Category
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterStatus
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterUiModel
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter

class CharacterMapper {

    companion object {
        private const val BREAKING_BAD_SEASONS = 5
    }

    fun mapToUiModel(it: List<BreakingBadCharacter>): List<CharacterUiModel> {
        return it.map {
            mapCharacterToUiModel(it)
        }
    }

    private fun mapCharacterToUiModel(breakingBadCharacter: BreakingBadCharacter) =
        CharacterUiModel(
            mapToBreakingBadCharacterEnum(breakingBadCharacter.appearance),
            breakingBadCharacter.betterCallSaulAppearance,
            breakingBadCharacter.birthday,
            mapToCategoryEnum(breakingBadCharacter.category),
            breakingBadCharacter.charId,
            breakingBadCharacter.img,
            breakingBadCharacter.name,
            breakingBadCharacter.nickname,
            breakingBadCharacter.occupation,
            breakingBadCharacter.portrayed,
            mapToStatusEnum(breakingBadCharacter.status)
        )

    private fun mapToBreakingBadCharacterEnum(appearances: List<Int>): List<BreakingBadSeason> {
        return when {
            appearances.isEmpty() -> listOf(BreakingBadSeason.NONE)
            appearances.size == BREAKING_BAD_SEASONS -> listOf(BreakingBadSeason.ALL)
            else -> {
                val listOfSeasons = mutableListOf<BreakingBadSeason>()

                if (appearances.contains(1)) listOfSeasons.add(BreakingBadSeason.SEASON_1)
                if (appearances.contains(2)) listOfSeasons.add(BreakingBadSeason.SEASON_2)
                if (appearances.contains(3)) listOfSeasons.add(BreakingBadSeason.SEASON_3)
                if (appearances.contains(4)) listOfSeasons.add(BreakingBadSeason.SEASON_4)
                if (appearances.contains(5)) listOfSeasons.add(BreakingBadSeason.SEASON_5)
                listOfSeasons.toList()
            }
        }
    }

    private fun mapToStatusEnum(status: String): CharacterStatus {
        return CharacterStatus.values().firstOrNull { it.value == status } ?: CharacterStatus.OTHER
    }

    private fun mapToCategoryEnum(category: String): Category {
        return Category.values().firstOrNull { it.value == category } ?: Category.OTHER
    }

}
