package com.pedroribeiro.breakingbadcharacterschallenge.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseViewModel
import com.pedroribeiro.breakingbadcharacterschallenge.common.SingleLiveEvent
import com.pedroribeiro.breakingbadcharacterschallenge.mappers.CharacterMapper
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterUiModel
import com.pedroribeiro.breakingbadcharacterschallenge.repositories.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val charactersRepository: CharactersRepository,
    private val characaterMapper: CharacterMapper
) : BaseViewModel() {

    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _characters = MutableLiveData<List<CharacterUiModel>>()
    val characters: LiveData<List<CharacterUiModel>> = _characters

    fun getCharacters() {
        compositeDispoosable.add(
            charactersRepository.getCharacters()
                .doOnSubscribe { _loading.postValue(true) }
                .doFinally { _loading.postValue(false) }
                .map { characaterMapper.mapToUiModel(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ characters ->
                    _characters.postValue(characters)
                }, {
                    _error.postValue(Unit)
                })
        )
    }

    fun onCharacterClicked(character: CharacterUiModel) {
        _navigation.postValue(Navigation.ToCharacterDetails(character))
    }

    sealed class Navigation {
        data class ToCharacterDetails(
            val character: CharacterUiModel
        ) : Navigation()
    }

}
