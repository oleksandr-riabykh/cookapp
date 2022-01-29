package com.alex.cooksample.ui.recipes.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alex.cooksample.data.repository.CollectionsRepository
import com.alex.cooksample.data.repository.RecipesRepository
import com.alex.cooksample.ui.recipes.detail.RecipeDetailViewModel
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
class RecipeDetailViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val repository: RecipesRepository = mockk(relaxed = true)
    private lateinit var viewModel: RecipeDetailViewModel


    @Before
    fun setupTest() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = RecipeDetailViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `If view model loadRecipes by id calls repository's getRecipeById method`() {
        testDispatcher.runBlockingTest {
            //given
            val recipeId = 2
            coEvery { repository.getRecipeById(recipeId) } returns mockk(relaxed = true)

            //when
            viewModel.loadRecipe(recipeId)

            //then
            coVerify { repository.getRecipeById(recipeId) }
        }
        testDispatcher.cleanupTestCoroutines()
    }

}