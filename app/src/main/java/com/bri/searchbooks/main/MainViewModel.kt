package com.bri.searchbooks.main

import android.util.Log
import com.bri.searchbooks.base.BaseViewModel
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {

    fun getBookList(query: String = "") {
        repository.getBookList(query).subscribeOn(Schedulers.io()).subscribe({ list ->
            Log.w("result", list.list.toString())
        }, { e -> e.printStackTrace() })
    }
}