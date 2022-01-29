package com.alex.cooksample.ui.recipes.detail

import com.alex.cooksample.ui.models.RecipeUIModel

sealed class RecipeDetailState {
    data class OnLoadCompleted(val data: RecipeUIModel) : RecipeDetailState()
    data class OnError(val error: Exception) : RecipeDetailState()
}