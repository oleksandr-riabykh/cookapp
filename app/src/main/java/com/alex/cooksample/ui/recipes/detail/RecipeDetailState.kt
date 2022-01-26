package com.alex.cooksample.ui.recipes.detail

import com.alex.cooksample.ui.base.BaseScreenState
import com.alex.cooksample.ui.models.RecipeUIModel

sealed class RecipeDetailState : BaseScreenState() {
    data class OnLoadCompleted(val data: RecipeUIModel) : RecipeDetailState()
    data class OnError(val error: Exception) : RecipeDetailState()
}