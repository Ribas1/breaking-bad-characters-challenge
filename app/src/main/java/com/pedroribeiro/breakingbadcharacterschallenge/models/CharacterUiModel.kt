package com.pedroribeiro.breakingbadcharacterschallenge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterUiModel(
    val appearances: List<BreakingBadSeason>,
    val betterCallSaulAppearances: List<Int>,
    val birthday: String,
    val category: Category,
    val charId: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: CharacterStatus
) : Parcelable

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
