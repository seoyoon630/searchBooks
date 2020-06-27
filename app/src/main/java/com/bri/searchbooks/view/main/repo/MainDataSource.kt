package com.bri.searchbooks.view.main.repo

import com.bri.searchbooks.data.BookList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainDataSource {
    //query	    String	검색을 원하는 질의어	O
    //page  	Integer	결과 페이지 번호, 1~50 사이의 값, 기본 값 1	X
    //size	    Integer	한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10	X
    //target	String	검색 필드 제한
    @GET("/v3/search/book")
    fun getBookList(
        @Query("query", encoded = false) query: String = "",
        @Query("page") page : Int,
        @Query("size") size: Int = 50,
        @Query("target") target : String = "title"
    ): Single<BookList>
}