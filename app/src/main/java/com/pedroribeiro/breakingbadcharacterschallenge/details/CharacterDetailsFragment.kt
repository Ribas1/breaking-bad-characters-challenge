package com.pedroribeiro.breakingbadcharacterschallenge.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pedroribeiro.breakingbadcharacterschallenge.R
import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseFragment
import com.pedroribeiro.breakingbadcharacterschallenge.common.textForIntro
import com.pedroribeiro.breakingbadcharacterschallenge.common.textForOccupations
import com.pedroribeiro.breakingbadcharacterschallenge.common.textIfAppeared
import kotlinx.android.synthetic.main.fragment_character_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : BaseFragment() {

    private val viewModel: CharacterDetailsViewModel by viewModel()
    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val character by lazy {
        args.character
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupView()
    }

    private fun setupToolbar() {
        toolbar_character_details.apply {
            setNavigationOnClickListener {
                viewModel.onUpClick()
            }
            title = character.name
        }
        Glide.with(requireView()).load(character.img).into(character_details_avatar)
    }

    private fun setupView() {
        character_details_intro.textForIntro(
            character.name,
            character.birthday,
            getString(R.string.character_intro)
        )
        character_details_occupations.textForOccupations(character.occupation)
        character_details_nickname.text = character.nickname
        character_details_status.text = character.status.value
    }

    private fun setupViewModel() {
        with(viewModel) {
            setup(character.appearances, character.betterCallSaulAppearances)

            appearances.observe(
                this@CharacterDetailsFragment,
                Observer { appearancesModel ->
                    onAppearances(appearancesModel)
                }
            )
        }
    }

    private fun onAppearances(appearancesModel: CharacterDetailsViewModel.AppearancesModel) {
        tv_show_details_appears_in_breaking_bad.textIfAppeared(
            getString(
                R.string.appears_in_seasons_breaking_bad,
                appearancesModel.breakingBadAppearances
            ),
            appearancesModel.breakingBadAppearances
        )
        tv_show_details_appears_in_better_call_saul.textIfAppeared(
            getString(
                R.string.appears_in_seasons_better_call_saul,
                appearancesModel.betterCallSaulAppearancesFormatted
            ),
            appearancesModel.betterCallSaulAppearancesFormatted
        )
    }
}