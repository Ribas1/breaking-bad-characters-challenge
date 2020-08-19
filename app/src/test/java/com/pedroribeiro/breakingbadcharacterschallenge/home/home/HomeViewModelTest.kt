package com.pedroribeiro.breakingbadcharacterschallenge.home.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.pedroribeiro.breakingbadcharacterschallenge.home.HomeViewModel
import com.pedroribeiro.breakingbadcharacterschallenge.home.utils.LifecycleOwnerUtils
import com.pedroribeiro.breakingbadcharacterschallenge.mappers.CharacterMapper
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterUiModel
import com.pedroribeiro.breakingbadcharacterschallenge.network.exceptions.SearchEmptyException
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter
import com.pedroribeiro.breakingbadcharacterschallenge.network.schedulers.TrampolineSchedulerProvider
import com.pedroribeiro.breakingbadcharacterschallenge.repositories.CharactersRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private val charactersRepository = mockk<CharactersRepository>(relaxed = true)
    private val charactersMapper = mockk<CharacterMapper>(relaxed = true)
    private val lifecycleOwner = mockk<LifecycleOwner>(relaxed = true)

    @Before
    fun setUp() {
        viewModel =
            HomeViewModel(
                charactersRepository,
                charactersMapper,
                TrampolineSchedulerProvider()
            )
        LifecycleOwnerUtils.setupLifecycleOwner(lifecycleOwner)
    }

    @Test
    fun getCharactersSuccessTest() {
        val observer = mockk<Observer<List<CharacterUiModel>>>(relaxed = true)
        val mockedResponse = mockk<List<BreakingBadCharacter>>(relaxed = true)
        val mockedUiModel = charactersMapper.mapToUiModel(mockedResponse)
        viewModel.characters.observe(lifecycleOwner, observer)
        every { charactersRepository.getCharacters() } returns Single.just(mockedResponse)

        viewModel.getCharacters()

        verify {
            observer.onChanged(mockedUiModel)
        }
    }

    @Test
    fun getCharactersSuccessFailed() {
        val observer = mockk<Observer<HomeViewModel.Error>>(relaxed = true)
        viewModel.error.observe(lifecycleOwner, observer)
        every { charactersRepository.getCharacters() } returns Single.error(Throwable())

        viewModel.getCharacters()

        verify {
            observer.onChanged(HomeViewModel.Error.Generic)
        }
    }

    @Test
    fun onCharacterClicked() {
        val observer = mockk<Observer<HomeViewModel.Navigation>>(relaxed = true)
        val mockedCharacterUiModel = mockk<CharacterUiModel>(relaxed = true)
        viewModel.navigation.observe(lifecycleOwner, observer)

        viewModel.onCharacterClicked(mockedCharacterUiModel)

        verify {
            observer.onChanged(
                HomeViewModel.Navigation.ToCharacterDetails(
                    mockedCharacterUiModel
                )
            )
        }
    }

    @Test
    fun searchCharacterSuccess() {
        val observer = mockk<Observer<List<CharacterUiModel>>>(relaxed = true)
        val mockedResponse = mockk<List<BreakingBadCharacter>>(relaxed = true)
        val mockedUiModel = charactersMapper.mapToUiModel(mockedResponse)
        val query = "Wa"
        viewModel.characters.observe(lifecycleOwner, observer)
        every { charactersRepository.searchCharacter(query) } returns Single.just(mockedResponse)

        viewModel.searchCharacter(query)

        verify {
            observer.onChanged(mockedUiModel)
        }
    }

    @Test
    fun searchCharacterFail() {
        val observer = mockk<Observer<HomeViewModel.Error>>(relaxed = true)
        val query = "Wa"
        viewModel.error.observe(lifecycleOwner, observer)
        every { charactersRepository.searchCharacter(query) } returns Single.error(Throwable())

        viewModel.searchCharacter(query)

        verify {
            observer.onChanged(HomeViewModel.Error.Generic)
        }
    }

    @Test
    fun searchCharacterFailWithNoResults() {
        val observer = mockk<Observer<HomeViewModel.Error>>(relaxed = true)
        val query = "Wa"
        viewModel.error.observe(lifecycleOwner, observer)
        every { charactersRepository.searchCharacter(query) } returns Single.error(
            SearchEmptyException()
        )

        viewModel.searchCharacter(query)

        verify {
            observer.onChanged(HomeViewModel.Error.NoSearchResults)
        }
    }
}