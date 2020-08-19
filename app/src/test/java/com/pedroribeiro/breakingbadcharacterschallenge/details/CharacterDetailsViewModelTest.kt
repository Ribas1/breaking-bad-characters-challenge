package com.pedroribeiro.breakingbadcharacterschallenge.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.pedroribeiro.breakingbadcharacterschallenge.home.utils.LifecycleOwnerUtils
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: CharacterDetailsViewModel
    private val lifecycleOwner = mockk<LifecycleOwner>(relaxed = true)

    @Before
    fun setUp() {
        viewModel = CharacterDetailsViewModel()
        LifecycleOwnerUtils.setupLifecycleOwner(lifecycleOwner)
    }

    @Test
    fun onUpClick() {
        val observer = mockk<Observer<CharacterDetailsViewModel.Navigation>>(relaxed = true)
        viewModel.navigation.observe(lifecycleOwner, observer)

        viewModel.onUpClick()

        verify {
            observer.onChanged(CharacterDetailsViewModel.Navigation.Up)
        }
    }
}