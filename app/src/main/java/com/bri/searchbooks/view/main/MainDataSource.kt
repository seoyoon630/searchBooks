package com.bri.searchbooks.view.main

import com.bri.searchbooks.data.BookList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainDataSource {
    //query	String	검색을 원하는 질의어	O
    //sort	String	결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy	X
    //page	Integer	결과 페이지 번호, 1~50 사이의 값, 기본 값 1	X
    //size	Integer	한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10	X
    //target	String	검색 필드 제한
//사용 가능한 값: title(제목), isbn (ISBN), publisher(출판사), person(인명)	X
    @GET("/v3/search/book")
    fun getBookList(
        @Query("query", encoded = false) query: String = "",
        @Query("size") size: Int = 50
    ): Single<BookList>
}