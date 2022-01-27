package com.alex.cooksample.data.repository

import com.alex.cooksample.data.network.CookService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipesRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val networkService: CookService = mockk(relaxed = true)
    private lateinit var recipesRepo: RecipesRepository

    @Before
    fun setupTest() {
        recipesRepo = RecipesRepository(networkService)
    }

    @Test
    fun `If recipes then get response from server`() {
        testDispatcher.runBlockingTest {
            //given
            coEvery { networkService.getRecipes() } returns mockk(relaxed = true)

            //when
            recipesRepo.getRecipes()

            //then
            coVerify { networkService.getRecipes() }
        }
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `If recipes by collection id then get response from server`() {
        testDispatcher.runBlockingTest {
            val collectionId = 2

            //given
            coEvery { networkService.getRecipesByCollectionId(collectionId) } returns mockk(relaxed = true)

            //when
            recipesRepo.getRecipesByCollectionId(collectionId)

            //then
            coVerify { networkService.getRecipesByCollectionId(collectionId) }
        }
        testDispatcher.cleanupTestCoroutines()

    }

    @Test
    fun `If recipes by id then get response from server`() {
        testDispatcher.runBlockingTest {
            val recipeId = 2

            //given
            coEvery { networkService.getRecipeById(recipeId) } returns mockk(relaxed = true)

            //when
            recipesRepo.getRecipeById(recipeId)

            //then
            coVerify { networkService.getRecipeById(recipeId) }
        }
        testDispatcher.cleanupTestCoroutines()
    }
}