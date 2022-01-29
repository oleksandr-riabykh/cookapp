package com.alex.cooksample.ui.collections.details

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
class CollectionDetailViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val collectionRepository: CollectionsRepository = mockk(relaxed = true)
    private val recipeRepository: RecipesRepository = mockk(relaxed = true)
    private lateinit var viewModel: CollectionDetailViewModel


    @Before
    fun setupTest() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = CollectionDetailViewModel(collectionRepository, recipeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `If view model loadCollection by id calls repository's getCollectionById method`() {
        testDispatcher.runBlockingTest {
            //given
            val collectionId = 2
            coEvery { collectionRepository.getCollectionById(collectionId) } returns mockk(relaxed = true)

            //when
            viewModel.loadCollection(collectionId)

            //then
            coVerify { collectionRepository.getCollectionById(collectionId) }
        }
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `If view model loadRecipes by id calls repository's getRecipesByCollectionId method`() {
        testDispatcher.runBlockingTest {
            //given
            val collectionId = 2
            coEvery { recipeRepository.getRecipesByCollectionId(collectionId) } returns mockk(
                relaxed = true
            )

            //when
            viewModel.loadRecipes(collectionId)

            //then
            coVerify { recipeRepository.getRecipesByCollectionId(collectionId) }
        }
        testDispatcher.cleanupTestCoroutines()
    }

}