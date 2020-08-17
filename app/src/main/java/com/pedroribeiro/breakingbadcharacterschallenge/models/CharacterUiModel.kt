package com.pedroribeiro.breakingbadcharacterschallenge.models

data class CharacterUiModel(
    val appearance: List<Int>,
    val betterCallSaulAppearance: List<Int>,
    val birthday: String,
    val category: Category,
    val charId: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: CharacterStatus
)

enum class CharacterStatus(val value: String) {
    ALIVE("Alive"), DECEASED("Deceased"), UNKNOWN("Unknown"), PRESUMED_DEAD("Presumed dead"), OTHER(
        "?"
    )
}

enum class Category(val value: String) {
    BREAKING_BAD("Breaking Bad"), BETTER_CALL_SAUL("Better Call Saul"), BOTH("Beaking Bad, Better Call Saul"), OTHER(
        "Other"
    )
}
