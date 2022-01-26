package com.alex.cooksample.ui.collections.detail

import com.alex.cooksample.ui.base.BaseScreenState
import com.alex.cooksample.ui.models.CookCollectionUIModel
import com.alex.cooksample.ui.models.RecipeUIModel

sealed class CollectionDetailState : BaseScreenState() {
    data class OnLoadCollectionCompleted(val data: CookCollectionUIModel) : CollectionDetailState()
    data class OnLoadRecipesCompleted(val data: List<RecipeUIModel>) : CollectionDetailState()
    data class OnRecipeClick(val itemId: Int) : CollectionDetailState()
    data class OnError(val error: Exception) : CollectionDetailState()
}