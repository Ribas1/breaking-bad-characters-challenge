package com.pedroribeiro.breakingbadcharacterschallenge.common

fun String.replaceSquareBrackets(): String {
    return this.replace("[", "").replace("]", "")
}