package com.alex.cooksample.ui.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alex.cooksample.data.repository.CollectionsRepository
import com.alex.cooksample.data.repository.RecipesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class RecipesViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val repository: RecipesRepository = mockk(relaxed = true)
    private lateinit var recipesViewModel: RecipesViewModel


    @Before
    fun setupTest() {
        Dispatchers.setMain(mainThreadSurrogate)
        recipesViewModel = RecipesViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `If view model loadRecipes calls repository's getRecipes method`() {
        testDispatcher.runBlockingTest {
            //given
            coEvery { repository.getRecipes() } returns mockk(relaxed = true)

            //when
            recipesViewModel.loadRecipes()

            //then
            coVerify { repository.getRecipes() }
        }
        testDispatcher.cleanupTestCoroutines()
    }

}