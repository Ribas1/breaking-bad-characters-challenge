package com.pedroribeiro.breakingbadcharacterschallenge.common

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

fun ProgressBar.show(isLoading: Boolean) {
    if (isLoading) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun TextView.visible() {
    this.visibility = View.VISIBLE
}

fun TextView.gone() {
    this.visibility = View.GONE
}

fun TextView.textForIntro(name: String, birthday: String, string: String) {
    this.text = """$name $string $birthday"""
}

fun TextView.textForOccupations(occupation: List<String>) {
    this.text = occupation.toString().replaceSquareBrackets()
}

fun TextView.textIfAppeared(
    string: String,
    apperances: String
) {
    if (apperances.isNotBlank()) {
        this.visibility = View.VISIBLE
        this.text = string
    }
}