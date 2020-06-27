package com.bri.searchbooks.view.main.repo

import com.bri.searchbooks.data.BookList
import io.reactivex.Single

interface MainRepository {
    fun getBookList(query: String = "", page: Int): Single<BookList>
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository {
    override fun getBookList(query: String, page: Int): Single<BookList> = dataSource.getBookList(query, page)
}