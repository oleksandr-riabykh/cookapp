package com.alex.cooksample.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.cooksample.data.repository.CollectionsRepository
import com.alex.cooksample.extensions.toCollectionUIModel
import com.alex.cooksample.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val collectionRepository: CollectionsRepository
) : ViewModel() {
    val state = SingleLiveEvent<CollectionState>()

    init {
        loadCollections()
    }

    fun loadCollections() {
        viewModelScope.launch {
            try {
                val data = collectionRepository.getCollections()
                state.postValue(CollectionState.OnLoadCompleted(data.map { it.toCollectionUIModel() }))
            } catch (e: Exception) {
                state.postValue(CollectionState.OnError(e))
            }
        }
    }
}