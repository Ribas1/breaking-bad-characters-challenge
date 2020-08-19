package com.pedroribeiro.breakingbadcharacterschallenge.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseViewModel
import com.pedroribeiro.breakingbadcharacterschallenge.common.SingleLiveEvent
import com.pedroribeiro.breakingbadcharacterschallenge.common.replaceSquareBrackets
import com.pedroribeiro.breakingbadcharacterschallenge.models.BreakingBadSeason

class CharacterDetailsViewModel : BaseViewModel() {

    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _appearances = MutableLiveData<AppearancesModel>()
    val appearances: LiveData<AppearancesModel> = _appearances

    fun onUpClick() {
        _navigation.postValue(Navigation.Up)
    }

    fun setup(appearances: List<BreakingBadSeason>, betterCallSaulAppearances: List<Int>) {
        val breakingBadAppearancesFormatted =
            appearances.map { it.value }.toList().toString().replaceSquareBrackets()
        val betterCallSaulAppearancesFormatted =
            betterCallSaulAppearances.toString().replaceSquareBrackets()
        _appearances.postValue(
            AppearancesModel(
                breakingBadAppearancesFormatted,
                betterCallSaulAppearancesFormatted
            )
        )
    }

    sealed class Navigation {
        object Up : Navigation()
    }

    data class AppearancesModel(
        val breakingBadAppearances: String,
        val betterCallSaulAppearancesFormatted: String
    )

}
