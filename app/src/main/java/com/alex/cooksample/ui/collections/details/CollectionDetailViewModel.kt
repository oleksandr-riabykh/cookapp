package com.alex.cooksample.ui.collections.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alex.cooksample.data.repository.CollectionsRepository
import com.alex.cooksample.data.repository.RecipesRepository
import com.alex.cooksample.extensions.toCollectionUIModel
import com.alex.cooksample.extensions.toRecipeUIModel
import com.alex.cooksample.ui.base.BaseViewModel
import com.alex.cooksample.ui.models.CookCollectionUIModel
import com.alex.cooksample.ui.models.RecipeUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionDetailViewModel @Inject constructor(
    private val collectionRepository: CollectionsRepository,
    private val recipeRepository: RecipesRepository
) : BaseViewModel() {
    val collectionUIModel = MutableLiveData<CookCollectionUIModel>()
    val recipesUIModel = MutableLiveData<List<RecipeUIModel>>()
    val error = MutableLiveData<Exception>()


    fun loadCollection(id: Int) {
        showLoadingIndicator.postValue(true)
        viewModelScope.launch {
            try {
                val data = collectionRepository.getCollectionById(id)
                collectionUIModel.postValue(data.toCollectionUIModel())
            } catch (e: Exception) {
                error.postValue(e)
            } finally {
                showLoadingIndicator.postValue(false)
            }
        }
    }

    fun loadRecipes(id: Int) {
        viewModelScope.launch {
            try {
                val data = recipeRepository.getRecipesByCollectionId(id)
                recipesUIModel.postValue(data.map { it.toRecipeUIModel() })
            } catch (e: Exception) {
                error.postValue(e)
            }
        }
    }
}