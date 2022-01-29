package com.alex.cooksample.ui.collections.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.cooksample.data.repository.CollectionsRepository
import com.alex.cooksample.data.repository.RecipesRepository
import com.alex.cooksample.extensions.toCollectionUIModel
import com.alex.cooksample.extensions.toRecipeUIModel
import com.alex.cooksample.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDetailViewModel @Inject constructor(
    private val collectionRepository: CollectionsRepository,
    private val recipeRepository: RecipesRepository
) : ViewModel() {
    val state = SingleLiveEvent<CollectionDetailState>()


    fun loadCollection(id: Int) {
        viewModelScope.launch {
            try {
                val data = collectionRepository.getCollectionById(id)
                state.postValue(CollectionDetailState.OnLoadCollectionCompleted(data.toCollectionUIModel()))
                loadRecipes(id)
            } catch (e: Exception) {
                state.postValue(CollectionDetailState.OnError(e))
            }
        }
    }

    private suspend fun loadRecipes(id: Int) {
        val data = recipeRepository.getRecipesByCollectionId(id)
        state.postValue(CollectionDetailState.OnLoadRecipesCompleted(data.map { it.toRecipeUIModel() }))
    }
}