package com.bri.searchbooks.main

import io.reactivex.Single

interface MainRepository {
    fun getBookList(query: String = ""): Single<BookList>
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository {
    override fun getBookList(query: String): Single<BookList> =
        dataSource.getBookList(query)
}