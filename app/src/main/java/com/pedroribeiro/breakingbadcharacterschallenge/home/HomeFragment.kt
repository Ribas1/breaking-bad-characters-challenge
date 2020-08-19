package com.pedroribeiro.breakingbadcharacterschallenge.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pedroribeiro.breakingbadcharacterschallenge.MainActivity
import com.pedroribeiro.breakingbadcharacterschallenge.R
import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseFragment
import com.pedroribeiro.breakingbadcharacterschallenge.common.ItemSpaceDecoration
import com.pedroribeiro.breakingbadcharacterschallenge.common.show
import com.pedroribeiro.breakingbadcharacterschallenge.models.BreakingBadSeason
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterUiModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val charactersAdapter: CharacterAdapter by lazy {
        CharacterAdapter { character: CharacterUiModel ->
            viewModel.onCharacterClicked(character)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setSupportActionBar(toolbar_home)
        setupRecyclerView()
        setupListeners()
    }

    private fun setupListeners() {
        bt_filter_all.setOnClickListener {
            viewModel.onSeasonFilterClick(BreakingBadSeason.ALL)
        }
        bt_filter_season_1.setOnClickListener {
            viewModel.onSeasonFilterClick(BreakingBadSeason.SEASON_1)
        }
        bt_filter_season_2.setOnClickListener {
            viewModel.onSeasonFilterClick(BreakingBadSeason.SEASON_2)
        }
        bt_filter_season_3.setOnClickListener {
            viewModel.onSeasonFilterClick(BreakingBadSeason.SEASON_3)
        }
        bt_filter_season_4.setOnClickListener {
            viewModel.onSeasonFilterClick(BreakingBadSeason.SEASON_4)
        }
        bt_filter_season_5.setOnClickListener {
            viewModel.onSeasonFilterClick(BreakingBadSeason.SEASON_5)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
        val search = menu.findItem(R.id.toolbar_search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchCharacter(query)
                //clearing focus for emulator testing purposes only since this callback get's triggered twice when hitting serach on the keyboard
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        with(rv_characters) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charactersAdapter
            addItemDecoration(
                ItemSpaceDecoration(
                    resources.getDimension(R.dimen.item_space).toInt()
                )
            )
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            getCharacters()

            characters.observe(
                this@HomeFragment,
                Observer { characters ->
                    onGetCharacters(characters)
                }
            )

            loading.observe(
                this@HomeFragment,
                Observer { isLoading ->
                    onLoading(isLoading)
                }
            )

            error.observe(
                this@HomeFragment,
                Observer { error ->
                    onError(error)
                }
            )

            navigation.observe(
                this@HomeFragment,
                Observer { navigation ->
                    onNavigation(navigation)
                }
            )
        }
    }

    private fun onNavigation(navigation: HomeViewModel.Navigation?) {
        when (navigation) {
            is HomeViewModel.Navigation.ToCharacterDetails -> onNavigateToCharacterDetails(
                navigation.character
            )
            null -> {
                //do nothing
            }
        }
    }

    private fun onNavigateToCharacterDetails(character: CharacterUiModel) {
        val navDirection = HomeFragmentDirections.actionFromHomeToCharacterDetails(
            character
        )
        navigateTo(navDirection)
    }

    private fun onError(error: HomeViewModel.Error) {
        when (error) {
            HomeViewModel.Error.CantSearchWhileLoadingError -> showSnackBar(getString(R.string.still_loading_error))
            HomeViewModel.Error.Generic -> showSnackBarWithAction(
                getString(R.string.generic_error),
                getString(R.string.reload)
            )
            HomeViewModel.Error.NoSearchResults -> showSnackBarWithAction(
                getString(R.string.search_error),
                getString(R.string.reload)
            )
        }
    }

    private fun showSnackBarWithAction(
        snackBarString: String,
        actionString: String = getString(R.string.retry)
    ) {
        Snackbar.make(
            requireView(),
            snackBarString,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(actionString) {
                viewModel.getCharacters()
            }
            .show()
    }

    private fun showSnackBar(snackbarText: String) {
        Snackbar.make(
            requireView(),
            snackbarText,
            Snackbar.LENGTH_LONG
        )
            .show()
    }

    private fun onLoading(isLoading: Boolean) {
        progress_bar.show(isLoading)
    }

    private fun onGetCharacters(characters: List<CharacterUiModel>) {
        charactersAdapter.updateData(characters)
    }
}