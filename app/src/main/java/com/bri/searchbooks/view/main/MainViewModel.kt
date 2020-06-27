package com.bri.searchbooks.view.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseViewModel
import com.bri.searchbooks.common.progress
import com.bri.searchbooks.data.Book
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {

    var query: String = ""
    var inputQuery: String = ""
    private var page: Int = 0
    var isSearching: Boolean = false

    val list = ObservableArrayList<Book?>()
    private val isEnd = ObservableBoolean(false)

    private val _showDetail = MutableLiveData<Book>()
    val showDetail: LiveData<Book> get() = _showDetail

    private val _backPressed = MutableLiveData<Boolean>()
    val backPressed: LiveData<Boolean> get() = _backPressed

    fun updateQuery() {
        this.query = inputQuery
        page = 0
        list.clear()
        isEnd.set(false)
        getBookList()
    }

    fun getBookList(pQuery: String = query) {
        if (!isEnd.get() && !isSearching) {
            page++
            isSearching = true

            repository.getBookList(pQuery, page).subscribeOn(Schedulers.io()).progress(if (page == 1) _isProgress else null)
                    .subscribe({ result ->
                        if (isEnd.get() != result.meta.is_end) isEnd.set(result.meta.is_end)    // 마지막 페이지 처리
                        list.addAll(result.list)        // 목록 처리
                        if (result.list.isEmpty()) list.add(null)
                        else list.remove(null)
                        isSearching = false
                    }, { e ->
                        e.printStackTrace()
                        isSearching = false
                    })
        } else if (isSearching && list.isNotEmpty()) _message.postValue(R.string.on_searching)
    }

    fun showDetail(book: Book) {
        _showDetail.postValue(book)
    }

    fun onBackPressed(){
        _backPressed.postValue(true)
    }
}