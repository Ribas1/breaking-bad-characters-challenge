package com.pedroribeiro.breakingbadcharacterschallenge.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseViewModel
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter
import com.pedroribeiro.breakingbadcharacterschallenge.repositories.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val charactersRepository: CharactersRepository
) : BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _characters = MutableLiveData<List<BreakingBadCharacter>>()
    val characters: LiveData<List<BreakingBadCharacter>> = _characters

    fun getCharacters() {
        compositeDispoosable.add(
            charactersRepository.getCharacters()
                .doOnSubscribe { _loading.postValue(true) }
                .doFinally { _loading.postValue(false) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ characters ->
                    _characters.postValue(characters)
                }, {
                    _error.postValue(Unit)
                })
        )
    }

}
