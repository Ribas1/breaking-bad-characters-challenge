package com.pedroribeiro.breakingbadcharacterschallenge.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseViewModel
import com.pedroribeiro.breakingbadcharacterschallenge.common.SingleLiveEvent
import com.pedroribeiro.breakingbadcharacterschallenge.mappers.CharacterMapper
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterUiModel
import com.pedroribeiro.breakingbadcharacterschallenge.network.exceptions.SearchEmptyException
import com.pedroribeiro.breakingbadcharacterschallenge.repositories.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val charactersRepository: CharactersRepository,
    private val characaterMapper: CharacterMapper
) : BaseViewModel() {

    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error> = _error

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
                    _error.postValue(Error.Generic)
                })
        )
    }

    fun onCharacterClicked(character: CharacterUiModel) {
        _navigation.postValue(Navigation.ToCharacterDetails(character))
    }

    fun searchCharacter(query: String?) {
        _loading.value?.let { isLoading ->
            if (!isLoading) {
                compositeDispoosable.add(
                    charactersRepository.searchCharacter(query ?: "")
                        .doOnSubscribe { _loading.postValue(true) }
                        .doFinally { _loading.postValue(false) }
                        .map { characaterMapper.mapToUiModel(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ characters ->
                            _characters.postValue(characters)
                        }, { throwable ->
                            onSearchError(throwable)
                        })
                )
            } else {
                _error.postValue(Error.CantSearchWhileLoadingError)
            }
        } ?: _error.postValue(Error.Generic)
    }

    private fun onSearchError(throwable: Throwable) {
        if (throwable is SearchEmptyException) {
            _error.postValue(Error.NoSearchResults)
        } else {
            _error.postValue(Error.Generic)
        }
    }

    sealed class Navigation {
        data class ToCharacterDetails(
            val character: CharacterUiModel
        ) : Navigation()
    }

    sealed class Error {
        object NoSearchResults : Error()
        object CantSearchWhileLoadingError : Error()
        object Generic : Error()
    }

}
