package com.bri.searchbooks.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.annotation.StringRes

/**
 * BaseActivity, BaseFragment 연결, 기본적인 로딩바 처리, 메세지 처리를 담당
 */
abstract class BaseViewModel : ViewModel() {
    protected val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> get() = _isProgress

    protected val _message = MutableLiveData<@StringRes Int>()
    val message: LiveData<Int> get() = _message
}