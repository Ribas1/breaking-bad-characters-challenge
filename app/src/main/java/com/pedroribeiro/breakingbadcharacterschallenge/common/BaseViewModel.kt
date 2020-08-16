package com.pedroribeiro.breakingbadcharacterschallenge.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected val compositeDispoosable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDispoosable.clear()
    }
}
