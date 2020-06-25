package com.bri.searchbooks.main

import com.google.gson.annotations.SerializedName

data class BookList(
    @SerializedName("documents") val list: ArrayList<Book>,
    val meta: Meta
)

data class Book(
    val authors: ArrayList<String>,     // 도서 저자 리스트
    val contents: String,               // 도서 소개
    val datetime: String,               // 도서 출판날짜, ISO 8601 형식
    val isbn: String,                   // 국제 표준 도서번호, ISBN10 또는 ISBN13
    val price: Int,                     // 도서 정가
    val publisher: String,              // 도서 출판사
    val sale_price: Int,                // 도서 판매가
    val status: String,                 // 도서 판매 상태 정보 (정상, 품절, 절판 등)
    val thumbnail: String,              // 도서 표지 미리보기 URL
    val title: String,                  // 도서 제목
    val translators: ArrayList<String>, // 도서 번역자 리스트
    val url: String                     // 도서 상세 URL
)

data class Meta(
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
)