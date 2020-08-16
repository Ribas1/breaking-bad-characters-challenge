package com.pedroribeiro.breakingbadcharacterschallenge.home

import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseViewModel
import com.pedroribeiro.breakingbadcharacterschallenge.repositories.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val charactersRepository: CharactersRepository
) : BaseViewModel() {
    fun getCharacters() {
        compositeDispoosable.add(
            charactersRepository.getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

}
