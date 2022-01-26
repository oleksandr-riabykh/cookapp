package com.alex.cooksample.ui.collections

import com.alex.cooksample.ui.base.BaseScreenState
import com.alex.cooksample.ui.models.CookCollectionUIModel

sealed class CollectionState : BaseScreenState() {
    data class OnLoadCompleted(val data: List<CookCollectionUIModel>) : CollectionState()
    data class OnClick(val itemId: Int) : CollectionState()
    data class OnError(val error: Exception) : CollectionState()
}