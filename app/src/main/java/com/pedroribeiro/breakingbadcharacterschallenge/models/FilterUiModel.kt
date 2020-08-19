package com.pedroribeiro.breakingbadcharacterschallenge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterUiModel(
    val selected: Boolean,
    val season: BreakingBadSeason
) : Parcelable

enum class BreakingBadSeason(val value: String) {
    ALL("All"), SEASON_1("Season 1"), SEASON_2("Season 2"), SEASON_3("Season 3"),
    SEASON_4("Season 4"), SEASON_5("Season 5"), NONE("none")
}