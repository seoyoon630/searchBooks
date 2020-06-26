package com.bri.searchbooks.view.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
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

    val isEnd = ObservableBoolean(false)

    fun getBookList(query: String = "") {
        repository.getBookList(query).subscribeOn(Schedulers.io()).subscribe({ result ->
            // 마지막 페이지 처리
            if (isEnd.get() != result.meta.is_end) isEnd.set(result.meta.is_end)
            // 목록 처리
            list.addAll(result.list)
        }, { e -> e.printStackTrace() })
    }
}