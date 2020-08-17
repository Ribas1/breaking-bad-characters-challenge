package com.pedroribeiro.breakingbadcharacterschallenge.common

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show(isLoading: Boolean) {
    if (isLoading) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}