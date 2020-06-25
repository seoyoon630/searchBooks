package com.bri.searchbooks.view.main

import com.bri.searchbooks.data.BookList
import io.reactivex.Single

interface MainRepository {
    fun getBookList(query: String = ""): Single<BookList>
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository {
    override fun getBookList(query: String): Single<BookList> =
        dataSource.getBookList(query)
}