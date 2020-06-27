package com.bri.searchbooks.view

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bri.searchbooks.R
import com.bri.searchbooks.base.BaseViewModel
import com.bri.searchbooks.common.progress
import com.bri.searchbooks.data.Book
import com.bri.searchbooks.view.main.repo.MainRepository
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {

    // 전문 parameter 검색어
    var query: String = ""

    // editText로 입력받는 검색어
    var inputQuery: String = ""

    // 현재 page
    private var page: Int = 0

    // 조회 sync 관리 flag
    var isSearching: Boolean = false

    // 결과
    val list = ObservableArrayList<Book?>()
    private val isEnd = ObservableBoolean(false)

    private val _showDetail = MutableLiveData<Book>()
    val showDetail: LiveData<Book> get() = _showDetail

    private val _backPressed = MutableLiveData<Boolean>()
    val backPressed: LiveData<Boolean> get() = _backPressed

    private val _openWeb = MutableLiveData<String>()
    val openWeb: LiveData<String> get() = _openWeb

    // 검색어 변경 처리
    fun updateQuery() {
        this.query = inputQuery
        page = 0
        list.clear()
        isEnd.set(false)
        getBookList()
    }

    // 도서 검색
    fun getBookList(pQuery: String = query) {
        if (!isEnd.get() && !isSearching) {
            page++
            isSearching = true

            repository.getBookList(pQuery, page).subscribeOn(Schedulers.io()).progress(if (page == 1) _isProgress else null)
                    .subscribe({ result ->
                        // 마지막 페이지 처리
                        if (isEnd.get() != result.meta.is_end) isEnd.set(result.meta.is_end)
                        // 목록 처리
                        // 결과가 0인 경우, null item을 추가하여 결과 없음을 표시
                        list.addAll(result.list)
                        if (result.list.isEmpty()) list.add(null)
                        else list.remove(null)
                        isSearching = false
                    }, { e ->
                        page--
                        _message.postValue(R.string.on_error)
                        e.printStackTrace()
                        isSearching = false
                    })

        } else if (isSearching && list.isNotEmpty()) _message.postValue(R.string.on_searching)
    }

    fun showDetail(book: Book) {
        _showDetail.postValue(book)
    }

    fun onBackPressed() {
        _backPressed.postValue(true)
    }

    fun openWeb(url: String) {
        _openWeb.postValue(url)
    }

    fun clearUrl() {
        _openWeb.postValue("")
    }
}