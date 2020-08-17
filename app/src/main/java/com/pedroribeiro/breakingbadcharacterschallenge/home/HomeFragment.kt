package com.pedroribeiro.breakingbadcharacterschallenge.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedroribeiro.breakingbadcharacterschallenge.R
import com.pedroribeiro.breakingbadcharacterschallenge.common.BaseFragment
import com.pedroribeiro.breakingbadcharacterschallenge.common.ItemSpaceDecoration
import com.pedroribeiro.breakingbadcharacterschallenge.common.show
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val DEFAULT_SPAN_COUNT = 2

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val charactersAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        charactersAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
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
        }
    }

    private fun onLoading(isLoading: Boolean) {
        progress_bar.show(isLoading)
    }

    private fun onGetCharacters(characters: List<BreakingBadCharacter>) {
        charactersAdapter.updateData(characters)
    }
}