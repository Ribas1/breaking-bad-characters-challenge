package com.pedroribeiro.breakingbadcharacterschallenge.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedroribeiro.breakingbadcharacterschallenge.R
import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseFragment

class CharacterDetailsFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }
}