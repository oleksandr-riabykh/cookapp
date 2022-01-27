package com.alex.cooksample.data.repository

import com.alex.cooksample.BaseTest
import com.alex.cooksample.data.network.CookService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RecipesRepositoryTest: BaseTest() {
    private val networkService: CookService = mockk(relaxed = true)

    private lateinit var recipesRepo: RecipesRepository

    @Before
    fun setupTest() {
        recipesRepo = RecipesRepository(networkService)
    }

    @Test
    fun `If recipes then get response from server`() = runBlocking {
        //given
        coEvery { networkService.getRecipes() } returns mockk(relaxed = true)

        //when
        recipesRepo.getRecipes()

        //then
        coVerify { networkService.getRecipes() }
    }


    @Test
    fun `If recipes by collection id then get response from server`() = runBlocking {

        val collectionId = 2

        //given
        coEvery { networkService.getRecipesByCollectionId(collectionId)} returns mockk(relaxed = true)

        //when
        recipesRepo.getRecipesByCollectionId(collectionId)

        //then
        coVerify { networkService.getRecipesByCollectionId(collectionId) }
    }

    @Test
    fun `If recipes by id then get response from server`() = runBlocking {

        val recipeId = 2

        //given
        coEvery { networkService.getRecipeById(recipeId)} returns mockk(relaxed = true)

        //when
        recipesRepo.getRecipeById(recipeId)

        //then
        coVerify { networkService.getRecipeById(recipeId) }
    }
}