package com.bri.searchbooks.view.main

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bri.searchbooks.base.BaseViewModel
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.data.BookList
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {

    private val _result = MutableLiveData<BookList>()
    val result: LiveData<BookList> get() = _result

    val list = ObservableArrayList<Book>()

    fun getBookList(query: String = "") {
        repository.getBookList(query).subscribeOn(Schedulers.io()).subscribe({ result ->
//            _result.postValue(result)
            list.addAll(result.list)
        }, { e -> e.printStackTrace() })
    }
}