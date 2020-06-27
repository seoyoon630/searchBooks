package com.bri.searchbooks.net

import com.bri.searchbooks.kakaoKey
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val request = chainRequest.newBuilder().apply {
            // 카카오 키 헤더
            addHeader("Authorization", kakaoKey)
        }.build()
        return chain.proceed(request)
    }
}