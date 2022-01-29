package com.alex.cooksample.ui.recipes

import com.alex.cooksample.ui.base.BaseScreenState
import com.alex.cooksample.ui.models.RecipeUIModel

sealed class RecipesState : BaseScreenState() {
    data class OnLoadCompleted(val data: List<RecipeUIModel>) : RecipesState()
    data class OnError(val error: Exception) : RecipesState()
}