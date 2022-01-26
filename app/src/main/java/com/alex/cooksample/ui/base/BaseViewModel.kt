package com.alex.cooksample.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val showLoadingIndicator = MutableLiveData<Boolean>()
}