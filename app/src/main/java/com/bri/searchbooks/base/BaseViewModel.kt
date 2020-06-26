package com.bri.searchbooks.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.annotation.StringRes

abstract class BaseViewModel : ViewModel() {
    protected val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> get() = _isProgress

    protected val _message = MutableLiveData<@StringRes Int>()
    val message: LiveData<Int> get() = _message
}