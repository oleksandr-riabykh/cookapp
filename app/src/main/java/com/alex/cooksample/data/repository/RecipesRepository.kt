package com.alex.cooksample.data.repository

import com.alex.cooksample.data.models.Recipe
import com.alex.cooksample.data.network.CookService
import javax.inject.Inject

// here might be some logic of syncing or data transformation etc
class RecipesRepository @Inject constructor(
    private val cookService: CookService
) {
    suspend fun getRecipes(): List<Recipe> = cookService.getRecipes()
    suspend fun getRecipesByCollectionId(id: Int) = cookService.getRecipesByCollectionId(id)
    suspend fun getRecipeById(id: Int) = cookService.getRecipeById(id)
}