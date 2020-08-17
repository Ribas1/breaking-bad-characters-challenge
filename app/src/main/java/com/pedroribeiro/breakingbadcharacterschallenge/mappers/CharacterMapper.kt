package com.pedroribeiro.breakingbadcharacterschallenge.mappers

import com.pedroribeiro.breakingbadcharacterschallenge.models.Category
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterStatus
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterUiModel
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter

class CharacterMapper {
    fun mapToUiModel(it: List<BreakingBadCharacter>): List<CharacterUiModel> {
        return it.map {
            mapCharacterToUiModel(it)
        }
    }

    private fun mapCharacterToUiModel(breakingBadCharacter: BreakingBadCharacter) =
        CharacterUiModel(
            breakingBadCharacter.appearance,
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

    private fun mapToStatusEnum(status: String): CharacterStatus {
        return CharacterStatus.values().firstOrNull { it.value == status } ?: CharacterStatus.OTHER
    }

    private fun mapToCategoryEnum(category: String): Category {
        return Category.values().firstOrNull { it.value == category } ?: Category.OTHER
    }

}
