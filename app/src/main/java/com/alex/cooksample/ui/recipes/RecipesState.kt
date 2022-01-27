package com.alex.cooksample.ui.recipes

import com.alex.cooksample.ui.models.RecipeUIModel

sealed class RecipesState {
    data class OnLoadCompleted(val data: List<RecipeUIModel>) : RecipesState()
    data class OnError(val error: Exception) : RecipesState()
}