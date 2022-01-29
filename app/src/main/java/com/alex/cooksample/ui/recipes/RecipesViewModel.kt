package com.alex.cooksample.ui.recipes

import androidx.lifecycle.viewModelScope
import com.alex.cooksample.data.repository.RecipesRepository
import com.alex.cooksample.extensions.toRecipeUIModel
import com.alex.cooksample.ui.base.BaseViewModel
import com.alex.cooksample.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipeRepository: RecipesRepository
) : BaseViewModel() {

    val state = SingleLiveEvent<RecipesState>()

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        showLoadingIndicator.postValue(true)
        viewModelScope.launch {
            try {
                val data = recipeRepository.getRecipes()
                state.postValue(RecipesState.OnLoadCompleted(data.map { it.toRecipeUIModel() }))
            } catch (e: Exception) {
                state.postValue(RecipesState.OnError(e))
            } finally {
                showLoadingIndicator.postValue(false)
            }
        }
    }
}