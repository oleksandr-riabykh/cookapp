package com.alex.cooksample.ui.recipes.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.cooksample.data.repository.RecipesRepository
import com.alex.cooksample.extensions.toRecipeUIModel
import com.alex.cooksample.ui.base.BaseViewModel
import com.alex.cooksample.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipesRepository
) : BaseViewModel() {

    val state = SingleLiveEvent<RecipeDetailState>()

    fun loadRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val data = recipeRepository.getRecipeById(id)
                state.postValue(RecipeDetailState.OnLoadCompleted(data.toRecipeUIModel()))
            } catch (e: Exception) {
                state.postValue(RecipeDetailState.OnError(e))
            }
        }
    }
}