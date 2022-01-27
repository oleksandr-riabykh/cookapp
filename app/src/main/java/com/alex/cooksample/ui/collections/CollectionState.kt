package com.alex.cooksample.ui.collections

import com.alex.cooksample.ui.models.CookCollectionUIModel

sealed class CollectionState {
    data class OnLoadCompleted(val data: List<CookCollectionUIModel>) : CollectionState()
    data class OnError(val error: Exception) : CollectionState()
}